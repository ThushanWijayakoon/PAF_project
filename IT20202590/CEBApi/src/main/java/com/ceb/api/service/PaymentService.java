package com.ceb.api.service;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ceb.api.dto.PaymentRequest;
import com.ceb.api.entity.Customer;
import com.ceb.api.exception.NotFoundException;
import com.ceb.api.repository.PaymentDaoImpl;

@Service
public class PaymentService {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

	private PaymentDaoImpl paymentDaoImpl;

	@Autowired
	public PaymentService(PaymentDaoImpl paymentDaoImpl) {
		super();
		this.paymentDaoImpl = paymentDaoImpl;
	}

	public int createNewPayment(PaymentRequest request) throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<------ Execute Get Customer By NIC:{} In Payment Service -------->", request.getNic());
		Customer customer = null;
		int createState = 0;
		try {
			customer = this.paymentDaoImpl.getCustomerByNic(request.getNic().trim());
			if (customer != null) {
				createState = this.paymentDaoImpl.createNewPayment(customer, request.getPaymentValue());
			}
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Couldn't Find Customer For This NIC : " + request.getNic());
		} catch (NullPointerException npe) {
			throw new NotFoundException("Couldn't Find Customer For This NIC : " + request.getNic());
		}
		return createState;
	}

	public int updatePayment(Integer paymentID, BigDecimal newPayValue)
			throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<------ Execute Update  In Payment Service Payment ID:{} - New Value:{}-------->", paymentID,
				newPayValue);
		return this.paymentDaoImpl.updatePayment(paymentID, newPayValue);
	}

	public int cancelPayment(Integer paymentNumber) throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<------ Execute Cancel Payment In Payment Service Payment ID:{} -------->", paymentNumber);

		return this.paymentDaoImpl.cancelPayment(paymentNumber);
	}
}

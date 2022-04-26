package com.ceb.api.repository;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ceb.api.entity.Customer;

public interface PaymentDao {
	public Customer getCustomerByNic(String nic)throws SocketTimeoutException, EmptyResultDataAccessException, DataAccessException, Exception;

	public int createNewPayment(Customer customer, BigDecimal paymentValue)throws SocketTimeoutException, DataAccessException, Exception;

	public int updatePayment(Integer paymentID, BigDecimal newPayValue)throws SocketTimeoutException, DataAccessException, Exception;

	public int cancelPayment(Integer paymentNumber)throws SocketTimeoutException, DataAccessException, Exception;
}

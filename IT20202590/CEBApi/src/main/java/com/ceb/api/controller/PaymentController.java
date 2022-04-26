package com.ceb.api.controller;

import java.net.SocketTimeoutException;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceb.api.dto.PaymentRequest;
import com.ceb.api.dto.SuccessMessage;
import com.ceb.api.exception.NotFoundException;
import com.ceb.api.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/NewPayment")
	public ResponseEntity<SuccessMessage> createNewPayment(@Valid @RequestBody PaymentRequest request)
			throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<-------- Execute Create New Payment In Payment Controller NIC:{} - Payment Value:{}",
				request.getNic(), request.getPaymentValue());
		int createState = 0;
		String message = "New Payment Create Failed !";

		createState = this.paymentService.createNewPayment(request);
		if (createState > 0)
			message = "New Payment Create Success !";

		return new ResponseEntity<SuccessMessage>(
				new SuccessMessage(HttpStatus.OK.toString(), message, new Date().toString()), HttpStatus.OK);
	}

	@PutMapping("/UpdatePayment")
	public ResponseEntity<SuccessMessage> updatePayment(@RequestParam(name = "paymentid") Integer paymentID,
			@Valid @RequestBody PaymentRequest request) throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<-------- Execute Update Payment In Payment Controller Payment ID:{} - New Payment Value:{}",
				paymentID, request.getPaymentValue());

		if (paymentID == null)
			throw new NotFoundException("The Payment ID Required ! Couldn't Find Payment");

		int updateState = 0;
		String message = "Payment Update Failed !";

		updateState = this.paymentService.updatePayment(paymentID, request.getPaymentValue());
		if (updateState > 0)
			message = "Payment Update Success !";

		return new ResponseEntity<SuccessMessage>(
				new SuccessMessage(HttpStatus.OK.toString(), message, new Date().toString()), HttpStatus.OK);
	}

	@DeleteMapping("/DeletePayment")
	public ResponseEntity<SuccessMessage> deletePayment(@RequestParam(name = "paymentid") Integer paymentID)
			throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<-------- Execute Delete Payment In Payment Controller Payment ID:{}", paymentID);

		if (paymentID == null)
			throw new NotFoundException("The Payment ID Required ! Couldn't Find Payment");

		int deleteState = 0;
		String message = "Payment Delete Failed !";

		deleteState = this.paymentService.cancelPayment(paymentID);
		if (deleteState > 0)
			message = "Payment Delete Success !";

		return new ResponseEntity<SuccessMessage>(
				new SuccessMessage(HttpStatus.OK.toString(), message, new Date().toString()), HttpStatus.OK);
	}
}

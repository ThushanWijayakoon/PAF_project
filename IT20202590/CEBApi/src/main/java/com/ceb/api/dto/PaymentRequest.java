package com.ceb.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

public class PaymentRequest {

	@NotBlank(message = "The NIC is required.")
	@Pattern(regexp = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$", flags = { Flag.CASE_INSENSITIVE,
			Flag.MULTILINE }, message = "The NIC is invalid.")
	private String nic;

	@NotNull(message = "The Payment Value Is Required !")
	@DecimalMin(value = "1.0", inclusive = false, message = "The Payment Value Shuld Gretter than 0.00")
	private BigDecimal paymentValue;

	public PaymentRequest() {
		super();
	}

	public PaymentRequest(String nic, BigDecimal paymentValue) {
		super();
		this.nic = nic;
		this.paymentValue = paymentValue;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public BigDecimal getPaymentValue() {
		return paymentValue;
	}

	public void setPaymentValue(BigDecimal paymentValue) {
		this.paymentValue = paymentValue;
	}

}

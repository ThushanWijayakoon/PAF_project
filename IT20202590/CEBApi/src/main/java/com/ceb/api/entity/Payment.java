package com.ceb.api.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
	private Integer payNumber;
	private BigDecimal payValue;
	private Date createDate;
	private String createBy;
	private short state;
	private Customer customer;

	public Payment() {
		super();

	}

	public Payment(Integer payNumber, BigDecimal payValue, Date createDate, String createBy, short state) {
		super();
		this.payNumber = payNumber;
		this.payValue = payValue;
		this.createDate = createDate;
		this.createBy = createBy;
		this.state = state;
	}

	public Integer getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(Integer payNumber) {
		this.payNumber = payNumber;
	}

	public BigDecimal getPayValue() {
		return payValue;
	}

	public void setPayValue(BigDecimal payValue) {
		this.payValue = payValue;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

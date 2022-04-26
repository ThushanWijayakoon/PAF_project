package com.ceb.api.entity;

import java.util.List;

public class Customer {
	private Integer customerID;
	private String customerName;
	private String nic;
	private String email;
	private String phone;

	private List<Payment> lstPayments;

	public Customer() {
		super();

	}

	public Customer(Integer customerID, String customerName, String nic, String email, String phone) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.nic = nic;
		this.email = email;
		this.phone = phone;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Payment> getLstPayments() {
		return lstPayments;
	}

	public void setLstPayments(List<Payment> lstPayments) {
		this.lstPayments = lstPayments;
	}

}

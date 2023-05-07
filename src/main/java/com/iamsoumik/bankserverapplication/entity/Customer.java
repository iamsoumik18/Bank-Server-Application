package com.iamsoumik.bankserverapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer")
public class Customer {

	@Id
	@Column(name = "customer_id")
	private int customerID;

	@Column(name = "customer_first_name")
	private String customerFirstName;

	@Column(name = "customer_last_name")
	private String customerLastName;

	public Customer() {
		this.customerID = 0;
		this.customerFirstName = "";
		this.customerLastName = "";
	}

	public Customer(int customerID, String customerFirstName, String customerLastName) {
		this.customerID = customerID;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + "]";
	}

}

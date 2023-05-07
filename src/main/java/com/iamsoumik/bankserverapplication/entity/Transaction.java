package com.iamsoumik.bankserverapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_transaction")
public class Transaction {

	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionID;

	@Column(name = "transaction_amount")
	private double transactionAmount;

	@Column(name = "transaction_type")
	private String transactionType;

	public Transaction() {
		this.transactionID = 0;
		this.transactionAmount = 0;
		this.transactionType = "";
	}

	public Transaction(double transactionAmount, String transactionType) {
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", transactionAmount=" + transactionAmount
				+ ", transactionType=" + transactionType + "]";
	}

}

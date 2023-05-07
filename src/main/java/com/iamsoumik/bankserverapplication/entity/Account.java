package com.iamsoumik.bankserverapplication.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_account")
public class Account {

	@Id
	@Column(name = "account_number")
	private int accountNumber;

	@Column(name = "account_balance")
	private double accountBalance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_number")
	private List<Transaction> transactionDB;

	public Account() {
		this.accountNumber = 0;
		this.accountBalance = 0;
		this.customer = new Customer();
		this.transactionDB = new ArrayList<>();
	}

	public Account(int accountNumber, Customer customer, List<Transaction> transactionDB) {
		this.accountNumber = accountNumber;
		this.customer = customer;
		this.transactionDB = transactionDB;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getTransactionDB() {
		return transactionDB;
	}

	public void setTransactionDB(List<Transaction> transactionDB) {
		this.transactionDB = transactionDB;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", customer="
				+ customer + ", transactionDB=" + transactionDB + "]";
	}

}

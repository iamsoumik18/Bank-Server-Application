package com.iamsoumik.bankserverapplication.service;

import java.util.List;

import com.iamsoumik.bankserverapplication.entity.Transaction;
import com.iamsoumik.bankserverapplication.exception.AccountAlreadyExistException;
import com.iamsoumik.bankserverapplication.exception.AccountNotFoundException;
import com.iamsoumik.bankserverapplication.exception.AccountNumberSameException;
import com.iamsoumik.bankserverapplication.exception.IncorrectAmountException;
import com.iamsoumik.bankserverapplication.exception.InsufficientBalanceException;

public interface BankService {
	
	boolean addAccount(int accountNumber, int customerID, String customerFirstName, String customerLastName) throws AccountAlreadyExistException;
	
	double showBalance(int accountNumber) throws AccountNotFoundException;
	
	double deposit(int accountNumber, double amount) throws IncorrectAmountException, AccountNotFoundException;
	
	double withdraw(int accountNumber, double amount) throws IncorrectAmountException, AccountNotFoundException, InsufficientBalanceException;
	
	double fundTransfer(int sourceAccountNumber, int targetAccountNumber, double amount) throws AccountNumberSameException, IncorrectAmountException, AccountNotFoundException, InsufficientBalanceException;
	
	List<Transaction> getAllTransaction(int accountNumber) throws AccountNotFoundException;

}

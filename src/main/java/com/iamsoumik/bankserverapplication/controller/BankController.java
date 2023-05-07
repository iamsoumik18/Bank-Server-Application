package com.iamsoumik.bankserverapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iamsoumik.bankserverapplication.entity.Transaction;
import com.iamsoumik.bankserverapplication.exception.AccountAlreadyExistException;
import com.iamsoumik.bankserverapplication.exception.AccountNotFoundException;
import com.iamsoumik.bankserverapplication.exception.AccountNumberSameException;
import com.iamsoumik.bankserverapplication.exception.IncorrectAmountException;
import com.iamsoumik.bankserverapplication.exception.InsufficientBalanceException;
import com.iamsoumik.bankserverapplication.service.BankService;

@RestController
public class BankController {

	@Autowired
	private BankService service;

	@PostMapping(value = "/addAccount/{accountNumber}/{customerID}/{customerFirstName}/{customerLastName}")
	public ResponseEntity<Boolean> addAccount(@PathVariable int accountNumber, @PathVariable int customerID,
			@PathVariable String customerFirstName, @PathVariable String customerLastName)
			throws AccountAlreadyExistException {
		return new ResponseEntity<>(service.addAccount(accountNumber, customerID, customerFirstName, customerLastName),
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/showBalance/{accountNumber}")
	public ResponseEntity<Double> showBalance(@PathVariable int accountNumber) throws AccountNotFoundException {
		return new ResponseEntity<>(service.showBalance(accountNumber), HttpStatus.OK);
	}

	@PutMapping(value = "/deposit/{accountNumber}/{amount}")
	public ResponseEntity<Double> deposit(@PathVariable int accountNumber, @PathVariable double amount)
			throws IncorrectAmountException, AccountNotFoundException {
		return new ResponseEntity<>(service.deposit(accountNumber, amount), HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/withdraw/{accountNumber}/{amount}")
	public ResponseEntity<Double> withdraw(@PathVariable int accountNumber, @PathVariable double amount)
			throws IncorrectAmountException, AccountNotFoundException, InsufficientBalanceException {
		return new ResponseEntity<>(service.withdraw(accountNumber, amount), HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/fundTransfer/{sourceAccountNumber}/{targetAccountNumber}/{amount}")
	public ResponseEntity<Double> fundTransfer(@PathVariable int sourceAccountNumber,
			@PathVariable int targetAccountNumber, @PathVariable double amount) throws AccountNumberSameException,
			IncorrectAmountException, AccountNotFoundException, InsufficientBalanceException {
		return new ResponseEntity<>(service.fundTransfer(sourceAccountNumber, targetAccountNumber, amount),
				HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/getTransactions/{accountNumber}")
	public ResponseEntity<List<Transaction>> getTransactions(@PathVariable int accountNumber)
			throws AccountNotFoundException {
		return new ResponseEntity<>(service.getAllTransaction(accountNumber), HttpStatus.OK);
	}

}

package com.iamsoumik.bankserverapplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamsoumik.bankserverapplication.entity.Account;
import com.iamsoumik.bankserverapplication.entity.Customer;
import com.iamsoumik.bankserverapplication.entity.Transaction;
import com.iamsoumik.bankserverapplication.exception.AccountAlreadyExistException;
import com.iamsoumik.bankserverapplication.exception.AccountNotFoundException;
import com.iamsoumik.bankserverapplication.exception.AccountNumberSameException;
import com.iamsoumik.bankserverapplication.exception.IncorrectAmountException;
import com.iamsoumik.bankserverapplication.exception.InsufficientBalanceException;
import com.iamsoumik.bankserverapplication.repository.BankRepository;
import com.iamsoumik.bankserverapplication.utility.Constant;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository repository;

	@Override
	public boolean addAccount(int accountNumber, int customerID, String customerFirstName, String customerLastName)
			throws AccountAlreadyExistException {
		Account account = new Account(accountNumber, new Customer(customerID, customerFirstName, customerLastName),
				new ArrayList<>());
		Optional<Account> optional = repository.findById(accountNumber);
		if (optional.isPresent()) {
			throw new AccountAlreadyExistException(Constant.ACCOUNT_ALREADY_EXIST);
		}
		repository.save(account);
		return true;
	}

	@Override
	public double showBalance(int accountNumber) throws AccountNotFoundException {
		Optional<Account> optional = repository.findById(accountNumber);
		if (optional.isEmpty()) {
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		}
		return optional.get().getAccountBalance();
	}

	@Override
	public double deposit(int accountNumber, double amount) throws IncorrectAmountException, AccountNotFoundException {
		if (amount <= 0) {
			throw new IncorrectAmountException(Constant.AMOUNT_INCORRECT);
		}
		Optional<Account> optional = repository.findById(accountNumber);
		if (optional.isEmpty()) {
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		}
		Account account = optional.get();
		account.setAccountBalance(account.getAccountBalance() + amount);
		account.getTransactionDB().add(new Transaction(amount, Constant.CREDIT));
		repository.save(account);
		return account.getAccountBalance();
	}

	@Override
	public double withdraw(int accountNumber, double amount)
			throws IncorrectAmountException, AccountNotFoundException, InsufficientBalanceException {
		if (amount <= 0) {
			throw new IncorrectAmountException(Constant.AMOUNT_INCORRECT);
		}
		Optional<Account> optional = repository.findById(accountNumber);
		if (optional.isEmpty()) {
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		}
		Account account = optional.get();
		if (account.getAccountBalance() < amount) {
			throw new InsufficientBalanceException(Constant.INSUFFICIENT_BALANCE);
		}
		account.setAccountBalance(account.getAccountBalance() - amount);
		account.getTransactionDB().add(new Transaction(amount, Constant.DEBIT));
		repository.save(account);
		return account.getAccountBalance();
	}

	@Override
	public double fundTransfer(int sourceAccountNumber, int targetAccountNumber, double amount)
			throws AccountNumberSameException, IncorrectAmountException, AccountNotFoundException,
			InsufficientBalanceException {
		if (sourceAccountNumber == targetAccountNumber) {
			throw new AccountNumberSameException(Constant.SOURCE_TARGET_ACCOUNT_SAME);
		}
		if (amount <= 0) {
			throw new IncorrectAmountException(Constant.AMOUNT_INCORRECT);
		}
		Optional<Account> optionalSource = repository.findById(sourceAccountNumber);
		if (optionalSource.isEmpty()) {
			throw new AccountNotFoundException(Constant.SOURCE_ACCOUNT_NOT_FOUND);
		}
		Account sourceAccount = optionalSource.get();
		if (sourceAccount.getAccountBalance() < amount) {
			throw new InsufficientBalanceException(Constant.INSUFFICIENT_BALANCE);
		}
		Optional<Account> optionalTarget = repository.findById(targetAccountNumber);
		if (optionalTarget.isEmpty()) {
			throw new AccountNotFoundException(Constant.TARGET_ACCOUNT_NOT_FOUND);
		}
		sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - amount);
		sourceAccount.getTransactionDB().add(new Transaction(amount, Constant.DEBIT));
		Account targetAccount = optionalTarget.get();
		targetAccount.setAccountBalance(targetAccount.getAccountBalance() + amount);
		targetAccount.getTransactionDB().add(new Transaction(amount, Constant.CREDIT));
		repository.saveAll(Arrays.asList(sourceAccount, targetAccount));
		return sourceAccount.getAccountBalance();
	}

	@Override
	public List<Transaction> getAllTransaction(int accountNumber) throws AccountNotFoundException {
		Optional<Account> optional = repository.findById(accountNumber);
		if (optional.isEmpty()) {
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		}
		Account account = optional.get();
		List<Transaction> transactions = account.getTransactionDB();
		return transactions.stream().sorted((o1, o2) -> Integer.compare(o2.getTransactionID(), o1.getTransactionID()))
				.limit(10).collect(Collectors.toList());
	}

}

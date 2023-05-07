package com.iamsoumik.bankserverapplication.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iamsoumik.bankserverapplication.exception.AccountAlreadyExistException;
import com.iamsoumik.bankserverapplication.exception.AccountNotFoundException;
import com.iamsoumik.bankserverapplication.exception.AccountNumberSameException;
import com.iamsoumik.bankserverapplication.exception.IncorrectAmountException;
import com.iamsoumik.bankserverapplication.exception.InsufficientBalanceException;

@ControllerAdvice
public class BankExceptionHandler {
	
	@ExceptionHandler(AccountAlreadyExistException.class)
	public ResponseEntity<String> handleAccountAlreadyExistException(AccountAlreadyExistException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountNumberSameException.class)
	public ResponseEntity<String> handleAccountNumberSameException(AccountNumberSameException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IncorrectAmountException.class)
	public ResponseEntity<String> handleIncorrectAmountException(IncorrectAmountException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
	}

}

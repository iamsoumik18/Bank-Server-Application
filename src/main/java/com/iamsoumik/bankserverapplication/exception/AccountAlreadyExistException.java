package com.iamsoumik.bankserverapplication.exception;

@SuppressWarnings("serial")
public class AccountAlreadyExistException extends Exception{
	
	public AccountAlreadyExistException(String message) {
		super(message);
	}

}

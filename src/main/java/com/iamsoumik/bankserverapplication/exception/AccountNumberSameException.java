package com.iamsoumik.bankserverapplication.exception;

@SuppressWarnings("serial")
public class AccountNumberSameException extends Exception{
	
	public AccountNumberSameException(String message) {
		super(message);
	}

}

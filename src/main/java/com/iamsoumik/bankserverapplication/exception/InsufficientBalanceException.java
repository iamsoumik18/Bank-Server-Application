package com.iamsoumik.bankserverapplication.exception;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception{
	
	public InsufficientBalanceException(String message) {
		super(message);
	}

}

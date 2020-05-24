package com.meritamerica.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldErrorException extends Exception {
	public FieldErrorException (String msg) {
		super(msg);
	}
}

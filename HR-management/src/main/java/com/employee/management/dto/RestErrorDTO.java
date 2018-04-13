package com.employee.management.dto;

import org.springframework.http.HttpStatus;

/*
 * Base class for adding error/exception in the response.
 */
public class RestErrorDTO {
	public final String message;
	public final HttpStatus code;

	public RestErrorDTO(Exception ex, HttpStatus pCode) {
		this.message = ex.getLocalizedMessage();
		this.code = pCode;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getCode() {
		return code;
	}
}

package com.employee.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.employee.management.dto.RestErrorDTO;
import com.employee.management.exception.DataFormatException;
import com.employee.management.exception.ResourceNotFoundException;

/**
 * Base Rest Controller class, which should be extended by all REST resource
 * "controllers" for handling exceptions for REST API functionality.
 */
public abstract class AbstractRestHandler implements RestHandler {

	protected final Logger log = LoggerFactory.getLogger(AbstractRestHandler.class);
	protected ApplicationEventPublisher eventPublisher;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataFormatException.class)
	@Override
	public @ResponseBody RestErrorDTO handleDataStoreException(DataFormatException ex) {
		log.info("Converting DataFormatException to Rest Response : " + ex.getMessage());

		return new RestErrorDTO(ex, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	@Override
	public @ResponseBody RestErrorDTO handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.info("Converting ResourceNotFoundException to Rest Response :" + ex.getMessage());

		return new RestErrorDTO(ex, HttpStatus.NOT_FOUND);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}
}
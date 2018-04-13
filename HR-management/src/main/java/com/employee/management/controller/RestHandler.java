package com.employee.management.controller;

import org.springframework.context.ApplicationEventPublisherAware;

import com.employee.management.dto.RestErrorDTO;
import com.employee.management.exception.DataFormatException;
import com.employee.management.exception.ResourceNotFoundException;

public interface RestHandler extends ApplicationEventPublisherAware{

	public RestErrorDTO handleDataStoreException(DataFormatException ex);
	
	public RestErrorDTO handleResourceNotFoundException(ResourceNotFoundException ex);
	
	default <T> T checkResourceFound(final T resource) {
		if (resource == null) {
			throw new ResourceNotFoundException("resource not found");
		}
		return resource;
	}
}

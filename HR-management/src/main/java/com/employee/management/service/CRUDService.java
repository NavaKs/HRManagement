package com.employee.management.service;

import java.io.Serializable;
import java.util.List;

/**
 * This is the Base Interface that should be implemented by all services.
 * 
 * @author Ravisankar Chinnam
 *
 * @param <E>
 */
public interface CRUDService<E> {

	void save(E entity);

	E getById(Serializable id);

	List<E> getAll();

}

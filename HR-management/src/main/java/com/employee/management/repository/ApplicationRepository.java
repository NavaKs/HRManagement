package com.employee.management.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.employee.management.model.Application;
import com.employee.management.model.Offer;

// interface extending crud repository
public interface ApplicationRepository extends CrudRepository<Application, Integer>{
    List<Application> findByOffer(Offer offer);
    Application findByAppId(int pAppId);
}

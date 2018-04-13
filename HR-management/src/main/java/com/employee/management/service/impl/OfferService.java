package com.employee.management.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.model.Offer;
import com.employee.management.repository.OfferRepository;
import com.employee.management.service.CRUDService;

@Service
public class OfferService implements CRUDService<Offer> {

    @Autowired
    private OfferRepository offerRepository;



    // fetching all Offers
    public List<Offer> getAll() {
        List<Offer> offers = (List<Offer>) offerRepository.findAll();
        return offers;
    }



    // fetching offer by jobTitle
    public Offer getById(Serializable id) {
        return offerRepository.findOne((String)id);
    }



    // create Offer
    public void save(Offer d) {
        offerRepository.save(d);
    }



    // check if offer is already created.
	public boolean isExistingOffer(Offer offer) {
		return getById(offer.getJobTitle()) != null;
	}

}

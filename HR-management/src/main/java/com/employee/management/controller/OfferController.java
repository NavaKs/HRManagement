package com.employee.management.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.exception.DataFormatException;
import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.model.Offer;
import com.employee.management.service.impl.OfferService;

@RestController
@RequestMapping("/offer")
public class OfferController extends AbstractRestHandler {

    static final Logger  logger = LogManager.getLogger(OfferController.class);

    @Autowired
    private OfferService offerService;



    // displaying list of all offers.
	@GetMapping(path = "/list", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody public ResponseEntity<List<Offer>> getAllOffers() {
		List<Offer> lOfferList = offerService.getAll();
		if (CollectionUtils.isEmpty(lOfferList)) {
			logger.error("Offers are not available...!");
			throw new ResourceNotFoundException("Offers are not available");
		}
		logger.debug("Found {} offers", lOfferList.size());
		logger.debug(Arrays.toString(lOfferList.toArray()));
		return new ResponseEntity<List<Offer>>(lOfferList, HttpStatus.OK);
	}



    // displaying offers by jobID/jobTitle.
	@GetMapping(path = "/{jobId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody public ResponseEntity<Offer> getOffer(@PathVariable("jobId") String jobId) {
		Offer lOffer = offerService.getById(jobId);
		if (null != lOffer) {
			logger.debug("Found Job Offer for: {} is  {}", jobId, lOffer);
			return new ResponseEntity<Offer>(lOffer, HttpStatus.OK);
		}
		logger.error("Job Offer: {} could not be retrieved or does not exists", jobId);
		throw new ResourceNotFoundException("could not be retrieved or does not exists");
	}



    // create new offer.
	@PostMapping(path = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
		if (offerService.isExistingOffer(offer)) {
			logger.error("Unable to create the Offer or already exists: {}", offer.toString());
			throw new DataFormatException("Unable to create the offer or already exists..!");
		}
		offerService.save(offer);
		logger.debug("A new Job Offer is created: ", offer);
		return new ResponseEntity<Offer>(offer, HttpStatus.CREATED);
	}

}

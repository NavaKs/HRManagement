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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.common.ApplicationStatusConvertor;
import com.employee.management.dto.TrackApplicationDTO;
import com.employee.management.exception.DataFormatException;
import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.model.Application;
import com.employee.management.model.ApplicationStatus;
import com.employee.management.service.impl.ApplicationService;
import com.employee.management.service.impl.OfferService;

@RestController
@RequestMapping("/application")
public class ApplicationController extends AbstractRestHandler{

	static final Logger logger = LogManager.getLogger(ApplicationController.class.getName());



	@Autowired
	private ApplicationService applicationService;



	@Autowired
	private OfferService offerService;



	// displaying list of all applications
	@GetMapping(path = "/list", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody public ResponseEntity<List<Application>> getAllApplication() {
		List<Application> lApplicationList = applicationService.getAll();
		if (CollectionUtils.isEmpty(lApplicationList)) {
			logger.error("Applications are not available...!");
			throw new ResourceNotFoundException("Applications are not available");
		}
		logger.debug("Found {} Applications", lApplicationList.size());
		logger.debug(Arrays.toString(lApplicationList.toArray()));

		return new ResponseEntity<List<Application>>(lApplicationList, HttpStatus.OK);
	}



	// displaying application by app id.
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody public ResponseEntity<Application> getApplicationById(@PathVariable int id){
		Application lApplication = applicationService.getById(id);
		if (null != lApplication) {
			logger.debug("Found Job Application for: {} is  {}", id, lApplication.toString());
			return new ResponseEntity<Application>(lApplication, HttpStatus.OK);
		}
		logger.error("Job Application: {} could not be retrieved or does not exists", id);
		throw new ResourceNotFoundException("could not be retrieved or does not exists");
	}



	// displaying all applications by per offer.
	@GetMapping(path = "/offer/{jobId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody public ResponseEntity<List<Application>> getAllApplicationsByJobId(@PathVariable String jobId){
		List<Application> lApplicationList = applicationService.getAllApplicationsByJobId(offerService.getById(jobId));
		if (CollectionUtils.isEmpty(lApplicationList)) {
			logger.error("Applications are not available for the offer: {}", jobId);
			throw new ResourceNotFoundException("Applications are not available for the offer: "+ jobId);
		}
		logger.debug("Found {} Applications", lApplicationList.size());
		logger.debug(Arrays.toString(lApplicationList.toArray()));

		return new ResponseEntity<List<Application>>(lApplicationList, HttpStatus.OK);
	}



	// create application
	@PostMapping(path = "/apply", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody public ResponseEntity<Application> createApplication(@RequestBody Application app){
		if (applicationService.isExistingApplication(app)) {
			logger.error("Unable to create the Application or already exists: {}", app.toString());
			throw new DataFormatException("Unable to create the offer or already exists..!");
		}
	    applicationService.save(app);
	    logger.debug("A new Job Application is created: ", app);
		return new ResponseEntity<Application>(app, HttpStatus.CREATED);
	}



	// updating application status by app id.
	@RequestMapping(value = "/update/{appId}/{pApplicationStatus}", method = RequestMethod.PUT)
	@ResponseBody public ResponseEntity<Void> updateApplicationStatus(@PathVariable int appId,
			@PathVariable ApplicationStatus pApplicationStatus) {
		try {
			Application app = applicationService.getById(appId);
			if (null != pApplicationStatus
					&& !org.springframework.util.StringUtils.isEmpty(pApplicationStatus.getTextValue())) {
				boolean result = applicationService.updateApplicationStatus(app, pApplicationStatus);
				if (result) {
					logger.debug("Updated the status of the Application: {} ", appId);
					return new ResponseEntity<Void>(HttpStatus.CREATED);
				}
			}
		} catch (Exception e) {
			checkResourceFound(e);
		}
		logger.error("Unable to update the status of the Application: {}", appId);
		throw new DataFormatException("Unable to update the status of the Application: " + appId);
	}
	


	// track the status of all applications.
	@GetMapping(path = "/track", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody public ResponseEntity<List<TrackApplicationDTO>> getTotalApplications(){
		List<TrackApplicationDTO> lTrackApplicationDTOList = applicationService.getTotalApplications(offerService.getAll());
		if (CollectionUtils.isEmpty(lTrackApplicationDTOList)) {
			logger.error("No Applications are not available in the System");
			throw new ResourceNotFoundException("No Applications are not available in the System");
		}
		logger.debug("Found {} Applications", lTrackApplicationDTOList.size());
		logger.debug(Arrays.toString(lTrackApplicationDTOList.toArray()));

		return new ResponseEntity<List<TrackApplicationDTO>>(lTrackApplicationDTOList, HttpStatus.OK);
    }



    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
     webdataBinder.registerCustomEditor(ApplicationStatus.class, new ApplicationStatusConvertor());
    }
}

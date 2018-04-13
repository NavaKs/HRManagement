package com.employee.management.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.dto.TrackApplicationDTO;
import com.employee.management.dto.NotificationDTO;
import com.employee.management.model.Application;
import com.employee.management.model.ApplicationStatus;
import com.employee.management.model.Offer;
import com.employee.management.repository.ApplicationRepository;
import com.employee.management.service.CRUDService;

import reactor.bus.Event;
import reactor.bus.EventBus;


@Service
public class ApplicationService implements CRUDService<Application>	{
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
    private EventBus eventBus;
	
	// fetching all applications.
	@Override
	public List<Application> getAll(){
		List<Application> app = (List<Application>)applicationRepository.findAll(); 
		return app;
	}
	
	// fetching application by id.
	@Override
	public Application getById(Serializable id){
		return applicationRepository.findOne((int)id);
	}
	
	// fetching all applications per offer by jobId.
    public List<Application> getAllApplicationsByJobId(Offer offer){
        return applicationRepository.findByOffer(offer);
    }
	
    @Override
	public void save(Application e) {
	    applicationRepository.save(e);
	}
	
	// updating application status by appId.
	public boolean updateApplicationStatus(Application app, ApplicationStatus pApplicationStatus){
	    app.setApplicationStatus(pApplicationStatus);
	    applicationRepository.save(app);
	    NotificationDTO data = new NotificationDTO();
	    data.setId(app.getAppId());
	    data.setName(pApplicationStatus.getTextValue());
	    // if success triggers a notification.
        eventBus.notify("notificationConsumer", Event.wrap(data));
        return true;
	}
	
	// get all applications per each offer and individual status count.
	public List<TrackApplicationDTO> getTotalApplications(List<Offer> pOfferList){
	    List<TrackApplicationDTO> statusByCount = new ArrayList<TrackApplicationDTO>();
	    pOfferList.forEach(offer->{
	    	List<Application> applicationList = getAllApplicationsByJobId(offer);
	    	TrackApplicationDTO lDTO = new TrackApplicationDTO();
	        statusByCount.add(lDTO);
	        lDTO.setJobId(offer.getJobId());
	        lDTO.setNoOfApplications(applicationList.size());
	        applicationList.forEach(application -> {
	        	switch (application.getApplicationStatus()) {
                case APPLIED:
                    populateStatusByCount(lDTO, application);
                    break;
                case REJECTED:
                    populateStatusByCount(lDTO, application);
                    break;
                case INVITED:
                    populateStatusByCount(lDTO, application);
                    break;
                case HIRED:
                    populateStatusByCount(lDTO, application);
                    break;
                }
	        });
	    });
	    return statusByCount;
	}

	/**
	 * @param pDTO
	 *            TrackApplicationDTO
	 * @param pApplication
	 *            Application
	 * @void
	 */
    private void populateStatusByCount(TrackApplicationDTO pDTO, Application pApplication) {
        int value = 0;
        if (null != pDTO.getStatus().get(pApplication.getApplicationStatus())) {
            value = pDTO.getStatus().get(pApplication.getApplicationStatus());
        }
        pDTO.getStatus().put(pApplication.getApplicationStatus(),
                value + 1);
    }



 // check if offer is already created.
 	public boolean isExistingApplication(Application pApplication) {
 		return getById(pApplication.getAppId()) != null;
 	}

}

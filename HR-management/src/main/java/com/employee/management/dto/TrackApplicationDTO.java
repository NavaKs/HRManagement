package com.employee.management.dto;

import java.util.HashMap;
import java.util.Map;

import com.employee.management.model.ApplicationStatus;

public class TrackApplicationDTO {

    private String                          jobId;
    private int                             noOfApplications;
    private Map<ApplicationStatus, Integer> status = new HashMap<ApplicationStatus, Integer>();



    /**
     * @return the noOfApplications
     */
    public int getNoOfApplications() {
        return this.noOfApplications;
    }



    /**
     * @param pNoOfApplications
     *            the noOfApplications to set
     */
    public void setNoOfApplications(int pNoOfApplications) {
        this.noOfApplications = pNoOfApplications;
    }



    /**
     * @return the jobId
     */
    public String getJobId() {
        return this.jobId;
    }



    /**
     * @param pJobId
     *            the jobId to set
     */
    public void setJobId(String pJobId) {
        this.jobId = pJobId;
    }



    /**
     * @return the status
     */
    public Map<ApplicationStatus, Integer> getStatus() {
        return this.status;
    }



    /**
     * @param pStatus
     *            the status to set
     */
    public void setStatus(Map<ApplicationStatus, Integer> pStatus) {
        this.status = pStatus;
    }
}

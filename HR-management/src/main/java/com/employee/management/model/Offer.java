package com.employee.management.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.employee.management.common.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "Offer")
public class Offer {

    @Id
    @Column(name = "job_Id")
    private String jobId;
    @Column(name="job_title" ,unique = true)
    private String jobTitle;
    private Date   startDate;
    private String numberOfApplications;



    public Offer() {

    }



    public Offer(String jobId) {
        super();
        this.jobId = jobId;
    }



    public Offer(String jobId,String jobTitle, Date start_date, String numberOf_Applications) {
        super();
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.startDate = start_date;
        this.numberOfApplications = numberOf_Applications;
    }



    /**
     * @return the jobTitle
     */
    public String getJobTitle() {
        return this.jobTitle;
    }



    /**
     * @param pJobTitle
     *            the jobTitle to set
     */
    public void setJobTitle(String pJobTitle) {
        this.jobTitle = pJobTitle;
    }



    /**
     * @return the startDate
     */

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getStartDate() {
        return this.startDate;
    }



    /**
     * @param pStartDate
     *            the startDate to set
     */
    public void setStartDate(Date pStartDate) {
        this.startDate = pStartDate;
    }



    /**
     * @return the numberOfApplications
     */
    public String getNumberOfApplications() {
        return this.numberOfApplications;
    }



    /**
     * @param pNumberOfApplications
     *            the numberOfApplications to set
     */
    public void setNumberOfApplications(String pNumberOfApplications) {
        this.numberOfApplications = pNumberOfApplications;
    }



    /**
     * @return the jobId
     */
    public String getJobId() {
        return this.jobId;
    }



    /**
     * @param pJobId the jobId to set
     */
    public void setJobId(String pJobId) {
        this.jobId = pJobId;
    }





}

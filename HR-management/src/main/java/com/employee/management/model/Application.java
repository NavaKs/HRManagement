package com.employee.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int    appId;
    @Column(name="candidate_email" ,unique = true)
    private String candidateEmail;
    private String resumeText;
    @Enumerated
    private ApplicationStatus applicationStatus;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "job_Id")
    private Offer  offer;



    public Application() {

    }



    public Application(String candidateEmail, String resumeText, ApplicationStatus applicationStatus,
            Offer offer) {
        super();
        this.candidateEmail = candidateEmail;
        this.resumeText = resumeText;
        this.applicationStatus = applicationStatus;
        this.offer = offer;
    }



    /**
     * @return the candidateEmail
     */
    public String getCandidateEmail() {
        return this.candidateEmail;
    }



    /**
     * @param pCandidateEmail the candidateEmail to set
     */
    public void setCandidateEmail(String pCandidateEmail) {
        this.candidateEmail = pCandidateEmail;
    }



    /**
     * @return the resumeText
     */
    public String getResumeText() {
        return this.resumeText;
    }



    /**
     * @param pResumeText the resumeText to set
     */
    public void setResumeText(String pResumeText) {
        this.resumeText = pResumeText;
    }



    /**
     * @return the applicationStatus
     */
    public ApplicationStatus getApplicationStatus() {
        return this.applicationStatus;
    }



    /**
     * @param pApplicationStatus the applicationStatus to set
     */
    public void setApplicationStatus(ApplicationStatus pApplicationStatus) {
        this.applicationStatus = pApplicationStatus;
    }



    /**
     * @return the offer
     */
    public Offer getOffer() {
        return this.offer;
    }



    /**
     * @param pOffer the offer to set
     */
    public void setOffer(Offer pOffer) {
        this.offer = pOffer;
    }
    
    


    /**
     * @return the appId
     */
    public int getAppId() {
        return this.appId;
    }



    /**
     * @param pAppId the appId to set
     */
    public void setAppId(int pAppId) {
        this.appId = pAppId;
    }
    
}

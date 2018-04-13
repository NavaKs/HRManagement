package com.employee.management.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.employee.management.model.ApplicationStatus;

public class ApplicationDTO {

	@NotNull
	private String relatedOffer;
	@NotNull
	@Email
	private String candidateEmail;
	@NotNull
	private String resumeText;
	@NotNull
	private ApplicationStatus applicationStaus;

	public String getRelatedOffer() {
		return relatedOffer;
	}

	public void setRelatedOffer(String relatedOffer) {
		this.relatedOffer = relatedOffer;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public ApplicationStatus getApplicationStaus() {
		return applicationStaus;
	}

	public void setApplicationStaus(ApplicationStatus applicationStaus) {
		this.applicationStaus = applicationStaus;
	}

}

package com.employee.management.common;

import java.beans.PropertyEditorSupport;

import com.employee.management.model.ApplicationStatus;

public class ApplicationStatusConvertor extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(ApplicationStatus.fromValue(text));
	}
}
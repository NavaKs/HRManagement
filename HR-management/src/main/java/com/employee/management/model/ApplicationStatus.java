
package com.employee.management.model;

public enum ApplicationStatus {

    APPLIED(0), INVITED(1), REJECTED(2), HIRED(3);

    private int mType;



    ApplicationStatus(int pType) {
        mType = pType;
    }



    /**
     * @return the mType
     */
    public int getType() {
        return mType;
    }



    /**
     * @param pType
     *            the mType
     */
    public void setType(int pType) {
        mType = pType;
    }



    public int getCode() {
        return mType;
    }



    public String getTextValue() {
        return name();
    }



    public static ApplicationStatus fromValue(String pValue) {
        for (ApplicationStatus application : values()) {
            if (application.mType == Integer.parseInt(pValue)) {
                return application;
            }
        }
        return null;
    }
}
package com.example.exceptionhandling.rest;

public class Error {

    private String errorName;
    private String errorDescription;

    public Error(String errorName, String errorDescription) {
        this.errorName = errorName;
        this.errorDescription = errorDescription;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}

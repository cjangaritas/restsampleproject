package com.restsample.data.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationErrorDTO {

    public ValidationErrorDTO(@JsonProperty("errorCode") String errorCode, @JsonProperty("message") String message ) {
        this.errorCode = errorCode;
        this.message = message;
    }

    private String errorCode;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


}

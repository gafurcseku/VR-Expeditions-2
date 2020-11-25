package com.robotlab.expeditions2.model;

public class Validation {
    private Boolean isValid;
    private String message;
    private String data;

    public Validation(Boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public Validation(Boolean isValid, String message, String data) {
        this.isValid = isValid;
        this.message = message;
        this.data = data;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

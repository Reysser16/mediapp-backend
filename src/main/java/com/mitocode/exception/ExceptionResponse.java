package com.mitocode.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private LocalDateTime date;
    private String massage;
    private String details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(LocalDateTime date, String massage, String details) {
        this.date = date;
        this.massage = massage;
        this.details = details;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

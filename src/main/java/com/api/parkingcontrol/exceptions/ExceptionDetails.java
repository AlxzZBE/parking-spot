package com.api.parkingcontrol.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

public class ExceptionDetails {
    private final LocalDateTime timeStamp;
    private final String title;
    private final Integer status;
    private final String details;

    public ExceptionDetails(RuntimeException e, String title) {
        this.timeStamp = LocalDateTime.now();
        this.title = title;
        this.status = e.getClass().getAnnotation(ResponseStatus.class).value().value();
        this.details = e.getMessage();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }
}
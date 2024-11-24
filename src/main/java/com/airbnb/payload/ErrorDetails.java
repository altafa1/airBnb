package com.airbnb.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ErrorDetails {

    private String message;

    public ErrorDetails(String message,Date date, String request) {
        this.date = date;
        this.request = request;
        this.message = message;
    }

    private Date date;
    private String request;
}

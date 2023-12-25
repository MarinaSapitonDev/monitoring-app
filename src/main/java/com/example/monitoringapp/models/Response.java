package com.example.monitoringapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
@Setter
@Getter
@Component
public class Response {
    private HttpStatus status;
    private String description;
}

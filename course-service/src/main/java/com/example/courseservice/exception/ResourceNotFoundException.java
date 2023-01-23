package com.example.courseservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Data not found")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
    }
}

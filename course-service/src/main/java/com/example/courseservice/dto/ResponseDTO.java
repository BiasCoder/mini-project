package com.example.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private Integer status;
    private String messages;
    private T data;
}

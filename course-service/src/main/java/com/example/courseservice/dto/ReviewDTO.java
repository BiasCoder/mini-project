package com.example.courseservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ReviewDTO implements Serializable {
    private Integer rating;
    private String note;
}

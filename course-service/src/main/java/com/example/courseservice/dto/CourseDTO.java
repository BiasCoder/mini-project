package com.example.courseservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseDTO implements Serializable {
    private String name;
    private Long certificate;
    private String type;
    private String status;
    private String level;
    private String description;
    private Long mentorId;
}

package com.example.courseservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LessonDTO implements Serializable {
    private String id;
    private String name;
    private String video;
    private String chapterId;
}

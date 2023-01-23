package com.example.courseservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChapterDTO implements Serializable {
    private String name;
}

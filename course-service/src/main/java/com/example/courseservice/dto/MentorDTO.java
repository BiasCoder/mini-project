package com.example.courseservice.dto;


import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MentorDTO implements Serializable {
    private String id;
    private String name;
    private String profile;
    private String email;
    private String profession;
}

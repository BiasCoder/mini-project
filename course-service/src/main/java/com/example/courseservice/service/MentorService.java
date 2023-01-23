package com.example.courseservice.service;

import com.example.courseservice.dto.MentorDTO;

import java.util.List;

public interface MentorService {
    MentorDTO createMentor(MentorDTO mentorDTO);
    MentorDTO updateMentor(Long id, MentorDTO mentorDTO);
    void deleteMentor(Long id);
    MentorDTO getMentorById(Long id);
    List<MentorDTO> getAllMentors();

}

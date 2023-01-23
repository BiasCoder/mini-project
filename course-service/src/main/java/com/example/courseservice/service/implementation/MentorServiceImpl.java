package com.example.courseservice.service.implementation;


import com.example.courseservice.dto.MentorDTO;
import com.example.courseservice.entity.Mentor;
import com.example.courseservice.exception.ResourceNotFoundException;
import com.example.courseservice.repository.MentorRepo;
import com.example.courseservice.service.MentorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorServiceImpl implements MentorService {

    private final MentorRepo mentorRepo;
    private final ModelMapper modelMapper;

    public MentorServiceImpl(MentorRepo mentorRepo, ModelMapper modelMapper) {
        this.mentorRepo = mentorRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public MentorDTO createMentor(MentorDTO mentorDTO) {
        Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
        mentor = mentorRepo.save(mentor);
        return modelMapper.map(mentor, MentorDTO.class);
    }
    @Override
    public MentorDTO updateMentor(Long id, MentorDTO mentorDTO) {
        Mentor mentor = mentorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id: " + id));
        modelMapper.map(mentorDTO, mentor);
        mentor = mentorRepo.save(mentor);
        return modelMapper.map(mentor, MentorDTO.class);
    }
    @Override
    public void deleteMentor(Long id) {
        mentorRepo.deleteById(id);
    }
    @Override
    public MentorDTO getMentorById(Long id) {
        Mentor mentor = mentorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentor not found"));
        return modelMapper.map(mentor, MentorDTO.class);
    }
    @Override
    public List<MentorDTO> getAllMentors() {
        List<Mentor> mentors = mentorRepo.findAll();
        return mentors.stream().map(mentor -> modelMapper.map(mentor, MentorDTO.class)).collect(Collectors.toList());
    }
}


package com.example.courseservice.controller;

import com.example.courseservice.dto.MentorDTO;
import com.example.courseservice.dto.ResponseDTO;
import com.example.courseservice.service.implementation.MentorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mentors")
public class MentorController {
    private final MentorServiceImpl mentorServiceImpl;

    public MentorController(MentorServiceImpl mentorServiceImpl) {
        this.mentorServiceImpl = mentorServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<MentorDTO>> createMentor(@RequestBody MentorDTO mentorDTO) {
        MentorDTO createdMentor = mentorServiceImpl.createMentor(mentorDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(HttpStatus.CREATED.value(),"Mentor berhasil ditambahkan", createdMentor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentorDTO> updateMentor(@PathVariable Long id, @RequestBody MentorDTO mentorDTO) {
        MentorDTO updatedMentor = mentorServiceImpl.updateMentor(id, mentorDTO);
        return ResponseEntity.ok(updatedMentor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id) {
        mentorServiceImpl.deleteMentor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> getMentorById(@PathVariable Long id) {
        MentorDTO mentor = mentorServiceImpl.getMentorById(id);
        return ResponseEntity.ok(mentor);
    }

    @GetMapping
    public ResponseEntity<List<MentorDTO>> getAllMentors() {
        List<MentorDTO> mentorDTOS = mentorServiceImpl.getAllMentors();
        return ResponseEntity.ok(mentorDTOS);
    }
}


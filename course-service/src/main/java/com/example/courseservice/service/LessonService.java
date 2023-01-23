package com.example.courseservice.service;

import com.example.courseservice.dto.LessonDTO;
import java.util.List;

public interface LessonService {
    List<LessonDTO> getAllLesson();
    LessonDTO getLessonById(Long id);
    void addLesson(LessonDTO lessonDTO);
    void updateLesson(LessonDTO lessonDTO, Long id);
    void deleteLesson(Long id);
}

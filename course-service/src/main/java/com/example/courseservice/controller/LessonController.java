package com.example.courseservice.controller;

import com.example.courseservice.dto.LessonDTO;
import com.example.courseservice.dto.ResponseDTO;

import com.example.courseservice.service.implementation.LessonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(" ")
public class LessonController {

    private final LessonServiceImpl lessonServiceimpl;

    public LessonController(LessonServiceImpl lessonServiceimpl) {
        this.lessonServiceimpl = lessonServiceimpl;
    }

    @GetMapping
    public List<LessonDTO> getAllLessons() {
        return lessonServiceimpl.getAllLesson();
    }

    @GetMapping("/{id}")
    public LessonDTO getLessonById(@PathVariable Long id) {
        return lessonServiceimpl.getLessonById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<LessonDTO>>addLesson(@RequestBody LessonDTO lessonDTO){
        lessonServiceimpl.addLesson(lessonDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(HttpStatus.CREATED.value(),"Lesson Berhasil Dibuat", lessonDTO));
    }

    @PutMapping("/{id}")
    public void updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        lessonServiceimpl.updateLesson(lessonDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonServiceimpl.deleteLesson(id);
    }
}

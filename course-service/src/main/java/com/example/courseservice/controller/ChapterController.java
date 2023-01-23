package com.example.courseservice.controller;

import com.example.courseservice.dto.ChapterDTO;
import com.example.courseservice.dto.ResponseDTO;
import com.example.courseservice.service.implementation.ChapterServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chapters")
public class ChapterController {

    private final ChapterServiceImpl chapterServiceimpl;

    public ChapterController(ChapterServiceImpl chapterServiceimpl) {
        this.chapterServiceimpl = chapterServiceimpl;
    }
    @GetMapping
    public List<ChapterDTO> getAllChapter() {
        return chapterServiceimpl.getAllChapter();
    }

    @GetMapping("/{id}")
    public ChapterDTO getChapterById(@PathVariable Long id) {
        return chapterServiceimpl.getChapterById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ChapterDTO>> addChapter(@RequestBody ChapterDTO chapterDTO){
        chapterServiceimpl.addChapter(chapterDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(HttpStatus.CREATED.value(),"Chapter Berhasil Dibuat", chapterDTO));
    }

    @PutMapping("/{id}")
    public void updateChapter(@PathVariable Long id, @RequestBody ChapterDTO chapterDTO) {
        chapterServiceimpl.updateChapter(chapterDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable Long id) {
        chapterServiceimpl.deleteChapter(id);
    }

}


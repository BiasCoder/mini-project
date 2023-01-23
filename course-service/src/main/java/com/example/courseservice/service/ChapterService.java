package com.example.courseservice.service;

import com.example.courseservice.dto.ChapterDTO;

import java.util.List;

public interface ChapterService {
    List<ChapterDTO> getAllChapter();
    ChapterDTO getChapterById(Long id);
    void addChapter(ChapterDTO chapterDTO);
    void updateChapter(ChapterDTO chapterDTO, Long id);
    void deleteChapter(Long id);
}

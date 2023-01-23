package com.example.courseservice.service.implementation;


import com.example.courseservice.dto.ChapterDTO;
import com.example.courseservice.dto.LessonDTO;
import com.example.courseservice.entity.Chapter;
import com.example.courseservice.entity.Lesson;
import com.example.courseservice.exception.ChapterNotFoundException;
import com.example.courseservice.exception.LessonNotFoundException;
import com.example.courseservice.repository.ChapterRepo;
import com.example.courseservice.service.ChapterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepo chapterRepo;

    private final ModelMapper modelMapper;

    public ChapterServiceImpl(ChapterRepo chapterRepo, ModelMapper modelMapper){
        this.chapterRepo = chapterRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ChapterDTO> getAllChapter() {
        List<Chapter> chapterList = chapterRepo.findAll();
        List<ChapterDTO> chapterDTOList = new ArrayList<>();
        for (Chapter chapter : chapterList){
            ChapterDTO chapterDTO = modelMapper.map(chapter, ChapterDTO.class);
            chapterDTOList.add(chapterDTO);
        }
        return chapterDTOList;
    }

    @Override
    public ChapterDTO getChapterById(Long id) {
        Optional<Chapter> chapter = chapterRepo.findById(id);
        if (!chapter.isPresent()) {
            throw new ChapterNotFoundException("Lesson with id: " + id + " not found");
        }
        return modelMapper.map(chapter.get(), ChapterDTO.class);
    }

    @Override
    public void addChapter(ChapterDTO chapterDTO) {
        Chapter chapter = modelMapper.map(chapterDTO, Chapter.class);
        chapterRepo.save(chapter);
    }

    @Override
    public void updateChapter(ChapterDTO chapterDTO, Long id) {
        Optional<Chapter> optionalChapter = chapterRepo.findById(id);
        if (!optionalChapter.isPresent()) {
            throw new ChapterNotFoundException("Chapter with id: " + id + " not found");
        }
        Chapter chapter = optionalChapter.get();
        chapter.setName(chapterDTO.getName());
        chapterRepo.save(chapter);
    }

    @Override
    public void deleteChapter(Long id) {
        Optional<Chapter> optionalChapter = chapterRepo.findById(id);
        if (!optionalChapter.isPresent()) {
            throw new ChapterNotFoundException("Chapter with id: " + id + " not found");
        }
        chapterRepo.delete(optionalChapter.get());
    }
}

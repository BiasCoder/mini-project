package com.example.courseservice.service.implementation;

import com.example.courseservice.dto.LessonDTO;
import com.example.courseservice.entity.Chapter;
import com.example.courseservice.entity.Lesson;
import com.example.courseservice.exception.ChapterNotFoundException;
import com.example.courseservice.exception.LessonNotFoundException;
import com.example.courseservice.repository.ChapterRepo;
import com.example.courseservice.repository.LessonRepository;
import com.example.courseservice.service.LessonService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @PersistenceContext
    private EntityManager entitiesManager;
    private final LessonRepository lessonRepository;

    private final ChapterRepo chapterRepo;
    private final ModelMapper modelMapper;

    public LessonServiceImpl(LessonRepository lessonRepository, ChapterRepo chapterRepo, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.chapterRepo = chapterRepo;
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(new PropertyMap<Lesson, LessonDTO>() {
            @Override
            protected void configure() {
                map().setChapterId(source.getChapter().getId().toString());
            }
        });
    }

    @Override
    public List<LessonDTO> getAllLesson() {
        List<Lesson> lessonList = lessonRepository.findAll();
        List<LessonDTO> lessonDTOList = new ArrayList<>();
        for (Lesson lesson : lessonList) {
            LessonDTO lessonDTO = modelMapper.map(lesson, LessonDTO.class);
            lessonDTOList.add(lessonDTO);
        }
        return lessonDTOList;
    }

    @Override
    public LessonDTO getLessonById(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (!lesson.isPresent()) {
            throw new LessonNotFoundException("Lesson with id: " + id + " not found");
        }
        return modelMapper.map(lesson.get(), LessonDTO.class);
    }

    @Override
    public void addLesson(LessonDTO lessonDTO) {
        Lesson lesson = modelMapper.map(lessonDTO, Lesson.class);
        if (lessonDTO.getChapterId() != null) {
            Chapter chapter = entitiesManager.find(Chapter.class, Long.valueOf(lessonDTO.getChapterId()));
            if (chapter == null) {
                throw new ChapterNotFoundException("Chapter with id: " + lessonDTO.getChapterId() + " not found");
            }
            lesson.setChapter(chapter);
        }
        lessonRepository.save(lesson);
    }


    @Override
    public void updateLesson(LessonDTO lessonDTO, Long id) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (!optionalLesson.isPresent()) {
            throw new LessonNotFoundException("Lesson with id: " + id + " not found");
        }
        Lesson lesson = optionalLesson.get();
        lesson.setName(lessonDTO.getName());
        lesson.setVideo(lessonDTO.getVideo());
        lesson.setChapterId(Long.valueOf(lessonDTO.getChapterId()));
        lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (!optionalLesson.isPresent()) {
            throw new LessonNotFoundException("Lesson with id: " + id + " not found");
        }
        lessonRepository.delete(optionalLesson.get());
    }
}


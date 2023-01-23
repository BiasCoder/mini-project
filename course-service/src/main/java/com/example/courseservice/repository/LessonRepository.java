package com.example.courseservice.repository;

import com.example.courseservice.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByChapterId(long chapterId);
    List<Lesson> findByName(String name);
    Optional<Lesson> findById(Integer id);
}


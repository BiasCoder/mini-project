package com.example.courseservice.repository;

import com.example.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
}

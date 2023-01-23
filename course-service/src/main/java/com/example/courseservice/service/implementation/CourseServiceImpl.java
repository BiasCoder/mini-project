package com.example.courseservice.service.implementation;


import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.entity.Course;
import com.example.courseservice.exception.ResourceNotFoundException;
import com.example.courseservice.repository.CourseRepo;
import com.example.courseservice.service.CourseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        courseRepo.save(course);
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        course.setName(courseDTO.getName());
        course.setCertificate(courseDTO.getCertificate());
        course.setType(courseDTO.getType());
        course.setStatus(courseDTO.getStatus());
        course.setLevel(courseDTO.getLevel());
        course.setDescription(courseDTO.getDescription());
        course.setMentorId(courseDTO.getMentorId());
        courseRepo.save(course);
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepo.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }
}

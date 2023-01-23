package com.example.courseservice.repository;

import com.example.courseservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
}

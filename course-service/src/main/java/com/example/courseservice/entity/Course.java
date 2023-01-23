package com.example.courseservice.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "course")
@Builder
@ToString

public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "certificate")
        private Long certificate;

        @Column(name = "type")
        private String type;

        @Column(name = "status")
        private String status;

        @Column(name = "level")
        private String level;

        @Column(name = "description")
        private String description;

        @Column(name = "mentor_id")
        private Long mentorId;

        @Column(name = "created_at", nullable = false, updatable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;


        @Column(name = "updated_at", nullable = false)
        @UpdateTimestamp
        private LocalDateTime updatedAt;

        // Getters and setters go here
        @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Chapter> chapters;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "mentor_id", referencedColumnName = "id", insertable = false, updatable = false)
        private Mentor mentor;

        @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
        private List<Mycourse> mycourses;
}



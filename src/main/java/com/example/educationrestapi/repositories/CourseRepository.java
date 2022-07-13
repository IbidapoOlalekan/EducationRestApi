package com.example.educationrestapi.repositories;

import com.example.educationrestapi.models.Courses;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<Courses,String> {
    Optional<Courses> findCoursesByCourseId(String courseId);
}

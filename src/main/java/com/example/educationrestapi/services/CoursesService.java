package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.AddCourseRequest;
import com.example.educationrestapi.dtos.responses.CourseDTO;

public interface CoursesService {
    CourseDTO createCourse(AddCourseRequest requests);
}

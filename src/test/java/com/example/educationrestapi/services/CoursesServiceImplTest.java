package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.AddCourseRequest;
import com.example.educationrestapi.dtos.responses.CourseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoursesServiceImplTest {
    @Autowired
    private CoursesService coursesService;

    private AddCourseRequest requests;

    @BeforeEach
    void setUp() {
        requests = AddCourseRequest.builder()
                .courseId("1")
                .courseName("Physics")
                .courseDuration(5)
                .courseFees(20000.0)
                .courseStartDate(LocalDateTime.now())
                .build();
    }

    @DisplayName("Courses Can Be Created")
    @Test
    void courseCanBeCreated(){
        CourseDTO courseDTO = coursesService.createCourse(requests);
        assertThat(courseDTO.getCourseId()).isNotNull();


    }
}
package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.AddCourseRequest;
import com.example.educationrestapi.dtos.responses.CourseDTO;
import com.example.educationrestapi.exceptions.CourseException;
import com.example.educationrestapi.models.Courses;
import com.example.educationrestapi.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CoursesServiceImpl implements CoursesService {
    private CourseRepository courseRepository;

    private ModelMapper modelmapper = new ModelMapper();

    public CoursesServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO createCourse(AddCourseRequest requests) {
        Optional<Courses> course = courseRepository.findCoursesByCourseId(requests.getCourseId());
        if (course.isPresent()) {
            throw new CourseException("Course Exists Already");
        } else {
            Courses courses = new Courses();
            courses.setCourseId(requests.getCourseId());
            courses.setCourseName(requests.getCourseName());
            courses.setCourseFees(requests.getCourseFees());
            courses.setCourseDuration(requests.getCourseDuration());
            courses.setCourseStartDate(requests.getCourseStartDate());
            courseRepository.save(courses);
            return modelmapper.map(courses,CourseDTO.class);
        }

    }
}

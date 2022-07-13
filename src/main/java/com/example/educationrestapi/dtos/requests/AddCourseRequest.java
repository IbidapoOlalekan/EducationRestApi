package com.example.educationrestapi.dtos.requests;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseRequest {
    private String courseId;
    private String courseName;
    private double courseFees;
    private int courseDuration;
    private LocalDateTime courseStartDate;
}

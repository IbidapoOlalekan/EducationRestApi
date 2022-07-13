package com.example.educationrestapi.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Courses {
    private String courseId;
    private String courseName;
    private double courseFees;
    private int courseDuration;
    private LocalDateTime courseStartDate ;

}

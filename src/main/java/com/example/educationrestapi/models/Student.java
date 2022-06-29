package com.example.educationrestapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String rollNo;
    private String firstName;
    private String lastName;
    private int age;
    private String contactNumber;
}

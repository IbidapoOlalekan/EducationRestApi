package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.CreateStudentRequest;
import com.example.educationrestapi.dtos.responses.StudentDTO;

public interface StudentService {
    StudentDTO createAccount(CreateStudentRequest requestDTO);
    StudentDTO findUser(String id);

    void deleteByRollNo(String s);
}

package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.CreateStudentRequest;
import com.example.educationrestapi.dtos.requests.UpdateStudentForm;
import com.example.educationrestapi.dtos.responses.StudentDTO;

public interface StudentService {
    StudentDTO createAccount(CreateStudentRequest requestDTO);
    StudentDTO findStudent(String id);

    String updateStudent(UpdateStudentForm updateForm);
    void deleteByRollNo(String s);
}

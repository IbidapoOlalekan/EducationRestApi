package com.example.educationrestapi.controllers;

import com.example.educationrestapi.dtos.requests.CreateStudentRequest;
import com.example.educationrestapi.dtos.requests.UpdateStudentForm;
import com.example.educationrestapi.exceptions.StudentException;
import com.example.educationrestapi.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1/EducationApi")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createStudentAccount(@RequestBody CreateStudentRequest createStudentRequest) {
        try {
            return new ResponseEntity<>(studentService.createAccount(createStudentRequest), HttpStatus.CREATED);
        }
        catch(StudentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateAccount")
    public ResponseEntity<?> updateAccount(@RequestBody UpdateStudentForm updateForm){
        try {
            return new ResponseEntity<>(studentService.updateStudent(updateForm),HttpStatus.OK);
        }
        catch(StudentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}

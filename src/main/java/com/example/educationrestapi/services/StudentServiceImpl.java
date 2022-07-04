package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.CreateStudentRequest;
import com.example.educationrestapi.dtos.requests.UpdateStudentForm;
import com.example.educationrestapi.dtos.responses.StudentDTO;
import com.example.educationrestapi.exceptions.StudentException;
import com.example.educationrestapi.models.Student;
import com.example.educationrestapi.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service

public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
//
    private ModelMapper modelmapper = new ModelMapper();

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public StudentDTO createAccount(CreateStudentRequest requestDTO) {
        Optional<Student> optionalStudent = studentRepository.findStudentByRollNo(requestDTO.getRollNo());

        if (optionalStudent.isPresent()) {
            throw new StudentException("Student Exist Already");
        }
        else {
            Student student = new Student();
            student.setRollNo(requestDTO.getRollNo());
            student.setFirstName(requestDTO.getFirstName());
            student.setLastName(requestDTO.getLastName());
            student.setAge(requestDTO.getAge());
            student.setContactNumber(requestDTO.getContactNumber());
            studentRepository.save(student);
            return modelmapper.map(student, StudentDTO.class);
        }
    }

    @Override
    public StudentDTO findStudent(String id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new StudentException("Student does not exist"));
        return modelmapper.map(student,StudentDTO.class);
    }

    @Override
    public String updateStudent(UpdateStudentForm updateForm) {
        Student student = studentRepository.findById(updateForm.getRollNo()).orElseThrow(()-> new StudentException("Student does not exist"));
        if (!(updateForm.getRollNo().trim().equals("") || updateForm.getRollNo()==null)){
            student.setRollNo(updateForm.getRollNo());
            studentRepository.save(student);
        }
        return "Student roll number have been updated";
    }

    @Override
    public void deleteByRollNo(String id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new StudentException("Student with roll no not found"));
        studentRepository.delete(student);
    }
}

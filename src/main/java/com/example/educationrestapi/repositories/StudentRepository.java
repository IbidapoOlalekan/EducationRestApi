package com.example.educationrestapi.repositories;

import com.example.educationrestapi.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {


    Optional<Student> findStudentByRollNo(String rollNo);

    Optional<Student> findStudentByFirstName(String firstName);


}

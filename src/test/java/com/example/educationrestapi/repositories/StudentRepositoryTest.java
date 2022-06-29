package com.example.educationrestapi.repositories;

import com.example.educationrestapi.models.Student;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class StudentRepositoryTest {
//    @DisplayName("given object to save" + " when save object using MongoDb Template" + " then object is saved")
//    @Test
//    public void saveTest(@Autowired MongoTemplate mongoTemplate){
//        DBObject objectToSave = BasicDBObjectBuilder.start()
//                .add("key","value")
//                .get();
//        //when
//        mongoTemplate.save(Student.class,"collection");
//
//        //then
//        assertThat(mongoTemplate.findAll(DBObject.class,"collection"))
//                .extracting("key").containsOnly("value");
//    }
    @Autowired
    private StudentRepository studentRepository;

    @Test void saveStudent(){
        var student = new Student("0","OLA","OL",6,"899");
        var students = new Student("1","OLA","OL",6,"899");
        studentRepository.save(student);
        studentRepository.save(students);
        assertEquals(2,studentRepository.findAll().size());

    }

    @Test void findStudentByRollNumber(){
        var student = new Student("0","Ola","Bayo",6,"090909030");
        studentRepository.save(student);
        studentRepository.findStudentByRollNo("0");
        assertEquals("0",student.getRollNo());
    }

    @Test void findStudentById(){
        var students = new Student("1","Bayo","Adamu",6,"09022263909");
        studentRepository.save(students);
        var studentInDb = studentRepository.findById(students.getRollNo());
        assertEquals("1", studentInDb.get().getRollNo());
    }


}
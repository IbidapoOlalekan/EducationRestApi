package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.CreateStudentRequest;
import com.example.educationrestapi.dtos.responses.StudentDTO;
import com.example.educationrestapi.exceptions.StudentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    private CreateStudentRequest requests;

    @BeforeEach
     void setUp(){
        requests = CreateStudentRequest.builder()
                .rollNo("10")
                .firstName("Barry")
                .lastName("Bruno")
                .age(21)
                .contactNumber("09022262609")
                .build();
    }

    @DisplayName("Student Can Register")
    @Test
    void studentCreateAccount(){
        StudentDTO studentDTO = studentService.createAccount(requests);
        assertThat(studentDTO.getRollNo()).isNotNull();
        assertThat(studentDTO.getFirstName()).isEqualTo("Barry");
    }
    @Test
    @DisplayName("When you try to create a student account with an email that already exist in DB," +
            "The create account service should throw a StudentException with the message: student" +
            " account already exists")
    void thatTestThrowsStudentExceptionExceptionWhenRollNoAlreadyExists(){
        studentService.createAccount(requests);
        CreateStudentRequest newAccountRequest = CreateStudentRequest.builder()
                .rollNo("10")
                .firstName("Barry")
                .lastName("Bruno")
                .age(21)
                .contactNumber("09022262609")
                .build();

        //assertThrows(StudentException.class, ()->studentService.createAccount(newAccountRequest));
        assertThatThrownBy(()->studentService.createAccount(newAccountRequest)).isInstanceOf(StudentException.class)
                .hasMessage("Student Exist Already");
    }

    @DisplayName("Can find User with roll no")
    @Test void testCanFindStudent(){
        StudentDTO studentDTO = studentService.createAccount(requests);
        StudentDTO studentFromDb = studentService.
                findUser(studentDTO.getRollNo());
        assertThat(studentDTO.getRollNo()).isEqualTo(studentFromDb.getRollNo());

    }
//@AfterEach
//void tearDown() {
//    studentService.deleteByRollNo("10");
//}
}
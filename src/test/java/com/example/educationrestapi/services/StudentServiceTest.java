package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.CreateStudentRequest;
import com.example.educationrestapi.dtos.requests.UpdateStudentForm;
import com.example.educationrestapi.dtos.responses.StudentDTO;
import com.example.educationrestapi.exceptions.StudentException;
import com.example.educationrestapi.models.Student;
import com.example.educationrestapi.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;


    private CreateStudentRequest requests;

    @BeforeEach
    void setUp() {
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
    void studentCreateAccount() {
        StudentDTO studentDTO = studentService.createAccount(requests);
        assertThat(studentDTO.getRollNo()).isNotNull();
        assertThat(studentDTO.getFirstName()).isEqualTo("Barry");
    }

    @Test
    @DisplayName("When you try to create a student account with an email that already exist in DB," +
            "The create account service should throw a StudentException with the message: Student" +
            " Exist Already")
    void thatTestThrowsStudentExceptionExceptionWhenRollNoAlreadyExists() {
        studentService.createAccount(requests);
        CreateStudentRequest newAccountRequest = CreateStudentRequest.builder()
                .rollNo("10")
                .firstName("Barry")
                .lastName("Bruno")
                .age(21)
                .contactNumber("09022262609")
                .build();

        //assertThrows(StudentException.class, ()->studentService.createAccount(newAccountRequest));
        assertThatThrownBy(() -> studentService.createAccount(newAccountRequest)).isInstanceOf(StudentException.class)
                .hasMessage("Student Exist Already");
    }

    @DisplayName("Can find User with roll no")
    @Test
    void testCanFindStudent() {
        StudentDTO studentDTO = studentService.createAccount(requests);
        StudentDTO studentFromDb = studentService.
                findStudent(studentDTO.getRollNo());
        assertThat(studentDTO.getRollNo()).isEqualTo(studentFromDb.getRollNo());

    }

    @Test
    @DisplayName("When you try to find a student account with an email that does not  exist in DB," +
            "The find student account service should throw a StudentException with the message: student" +
            " does not exist")
    void thatTestThrowsStudentExceptionExceptionWhenStudentFoundDoesNotExists() {
        studentService.createAccount(requests);
        assertThatThrownBy(() -> studentService.findStudent("12"))
                .isInstanceOf(StudentException.class)
                .hasMessage("Student does not exist");
    }

    @Test
    @DisplayName("When you try to update a student with new or correct details, " +
            "The update student service would change the necessary details and return a message: student" +
            "account have been updated")
    void updateStudentDetails() {
        var response = studentService.createAccount(requests);
        UpdateStudentForm updateStudentDetails = UpdateStudentForm.builder()
                .rollNo(requests.getRollNo())
                .newRollNo("4")
                .build();
       var res =  studentService.updateStudent(updateStudentDetails);
        assertThat(res).isEqualTo("Student roll number have been updated");
    }


    @AfterEach
    void tearDown() {
        studentService.deleteByRollNo("10");
    }
}
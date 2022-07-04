package com.example.educationrestapi.services;

import com.example.educationrestapi.dtos.requests.CreateStudentRequest;
import com.example.educationrestapi.dtos.responses.StudentDTO;
import com.example.educationrestapi.exceptions.StudentException;
import com.example.educationrestapi.models.Student;
import com.example.educationrestapi.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest

class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

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
                findStudent(studentDTO.getRollNo());
        assertThat(studentDTO.getRollNo()).isEqualTo(studentFromDb.getRollNo());

    }

    @Test
    @DisplayName("When you try to find a student account with an email that does not  exist in DB," +
            "The find student account service should throw a StudentException with the message: student" +
            " does not exist")
    void thatTestThrowsStudentExceptionExceptionWhenStudentFoundDoesNotExists(){
        studentService.createAccount(requests);
        assertThatThrownBy(()-> studentService.findStudent("12"))
                .isInstanceOf(StudentException.class)
                .hasMessage("Student does not exist");
    }

    @DisplayName("Delete a student with roll no Test")
    @Test void deleteStudentTest(){
        StudentDTO studentDTO = studentService.createAccount(requests);
        StudentDTO student = studentService.findStudent(studentDTO.getRollNo());
        studentService.deleteByRollNo(studentDTO.getRollNo());
        Optional<Student> studentFromDb = studentRepository.findStudentByRollNo(student.getRollNo());
        assertThat(studentFromDb).isNotPresent();
    }




//@AfterEach
//void tearDown() {
//    studentService.deleteByRollNo("10");
//}
}
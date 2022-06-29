//package com.example.educationrestapi.mappers;
//
//import com.example.educationrestapi.dtos.responses.StudentDTO;
//import com.example.educationrestapi.models.Student;
//
//import javax.annotation.processing.Generated;
//
//@Generated(
//        value = "org.mapstruct.ap.MappingProcessor",
//        date = "2022-06-19T11:07:53+0100",
//        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)")
//
//public class StudentMapperImpl implements StudentMapper{
//    @Override
//    public StudentDTO studentToStudentDTO(Student student) {
//        if (student == null){
//            return null;
//        }
//
//        StudentDTO studentDTO = new StudentDTO();
//
//        studentDTO.setRollNo(student.getRollNo());
//        studentDTO.setFirstName(student.getFirstName());
//
//        return studentDTO;
//    }
//}

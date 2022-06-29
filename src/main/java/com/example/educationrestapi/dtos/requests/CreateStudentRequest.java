package com.example.educationrestapi.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateStudentRequest {
    private String rollNo;
    private String firstName;
    private String lastName;
    private int age;
    private String contactNumber;

    @Override
    public String toString() {
        return "CreateStudentRequest{" + "rollNo=" + rollNo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}

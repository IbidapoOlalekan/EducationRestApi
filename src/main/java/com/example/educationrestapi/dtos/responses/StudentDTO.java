package com.example.educationrestapi.dtos.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    private String rollNo;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StudentDTO{");
        sb.append("rollNo='").append(rollNo).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}

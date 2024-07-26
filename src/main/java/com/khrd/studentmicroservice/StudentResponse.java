package com.khrd.studentmicroservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Integer id;
    private String name;
    private int age;
    private String gender;
    private School school;
}

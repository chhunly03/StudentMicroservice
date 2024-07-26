package com.khrd.studentmicroservice;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentRequest {
    private String name;
    private int age;
    private String gender;
    private Integer schoolId;
}

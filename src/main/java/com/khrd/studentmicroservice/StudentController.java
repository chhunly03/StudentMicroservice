package com.khrd.studentmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchStudentById(@PathVariable Integer id){
        return studentService.fetchStudentById(id);
    }
    @GetMapping
    public ResponseEntity<?> fetchStudents(){
        return studentService.fetchStudents();
    }
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest student){
        return studentService.createStudent(student);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id , StudentRequest student){
        return studentService.updateStudent(id ,student);
    }

}

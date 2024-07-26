package com.khrd.studentmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> createStudent(StudentRequest studentRequest) {
        try {
            Student student = new Student();
            student.setName(studentRequest.getName());
            student.setAge(studentRequest.getAge());
            student.setGender(studentRequest.getGender());
            student.setSchoolId(studentRequest.getSchoolId());

            Student savedStudent = studentRepository.save(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchStudentById(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            School school = restTemplate.getForObject("http://SCHOOLMICROSERVICE/school/" + student.get().getSchoolId(), School.class);
            System.out.println("fetch student by id: "+school);
            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school
            );
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Student Found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>("No Students", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> updateStudent(Integer id,StudentRequest Ustudent) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            Student oldData = student.get();
            oldData.setName(Ustudent.getName());
            oldData.setAge(Ustudent.getAge());
            oldData.setGender(Ustudent.getGender());
            oldData.setSchoolId(Ustudent.getSchoolId());

            Student students = studentRepository.save(oldData);
            return ResponseEntity.ok(students);
        }else {
            return null;
        }
    }
}

package com.example.demo;

import com.example.demo.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> studentList = new ArrayList<>();

    // 1. Add a student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // 2. Retrieve all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    // 3. Search for a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // 4. Update student scores and skills
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long id, @RequestBody Student updatedDetails) {
        Optional<Student> existingStudent = studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setScore(updatedDetails.getScore());
            student.setSkills(updatedDetails.getSkills());
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // 5. Delete a student record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean removed = studentList.removeIf(s -> s.getId().equals(id));

        if (removed) {
            return new ResponseEntity<>("Student record deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // 6. Display the merit list (Sorted by score highest to lowest)
    @GetMapping("/merit-list")
    public ResponseEntity<List<Student>> getMeritList() {
        List<Student> meritList = studentList.stream()
                .sorted(Comparator.comparingDouble(Student::getScore).reversed())
                .collect(Collectors.toList());

        return new ResponseEntity<>(meritList, HttpStatus.OK);
    }
}

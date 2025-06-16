package com.purva.trainingplatform.controller;

import com.purva.trainingplatform.dto.StudentDTO;
import com.purva.trainingplatform.model.Batch;
import com.purva.trainingplatform.model.Student;
import com.purva.trainingplatform.service.BatchRecommendationService;
import com.purva.trainingplatform.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final BatchRecommendationService recommendationService;

    public StudentController(StudentService studentService,
                             BatchRecommendationService recommendationService) {
        this.studentService = studentService;
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setPreviousCourses(studentDTO.getPreviousCourses());
        student.setAppliedSubject(studentDTO.getAppliedSubject());

        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @Valid @RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setPreviousCourses(studentDTO.getPreviousCourses());
        student.setAppliedSubject(studentDTO.getAppliedSubject());

        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/recommendations")
    public ResponseEntity<List<Batch>> getRecommendations(@PathVariable Long id) {
        return ResponseEntity.ok(recommendationService.recommendBatchesForStudent(id));
    }
}

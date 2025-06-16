package com.purva.trainingplatform.service;

import com.purva.trainingplatform.model.Student;
import com.purva.trainingplatform.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor (no Lombok used)
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create
    public Student addStudent(@Valid Student student) {
        return studentRepository.save(student);
    }

    // Read (by ID)
    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // Read (all)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Update
    public Student updateStudent(Long id, @Valid Student updatedStudent) {
        Student existing = getStudentById(id);

        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setPhone(updatedStudent.getPhone());
        existing.setDateOfBirth(updatedStudent.getDateOfBirth());
        existing.setPreviousCourses(updatedStudent.getPreviousCourses());
        existing.setAppliedSubject(updatedStudent.getAppliedSubject());

        return studentRepository.save(existing);
    }

    // Delete
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

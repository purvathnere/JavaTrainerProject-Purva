package com.purva.trainingplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private LocalDate dateOfBirth;

    @ElementCollection
    private List<String> previousCourses;

    @NotBlank
    private String appliedSubject;

    // Default constructor
    public Student() {}

    // All-args constructor
    public Student(Long id, String name, String email, String phone,
                   LocalDate dateOfBirth, List<String> previousCourses, String appliedSubject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.previousCourses = previousCourses;
        this.appliedSubject = appliedSubject;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getPreviousCourses() {
        return previousCourses;
    }

    public void setPreviousCourses(List<String> previousCourses) {
        this.previousCourses = previousCourses;
    }

    public String getAppliedSubject() {
        return appliedSubject;
    }

    public void setAppliedSubject(String appliedSubject) {
        this.appliedSubject = appliedSubject;
    }
}


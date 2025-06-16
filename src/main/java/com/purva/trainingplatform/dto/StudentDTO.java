package com.purva.trainingplatform.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class StudentDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid phone number format")
    private String phone;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private List<String> previousCourses;

    @NotBlank(message = "Applied subject is required")
    private String appliedSubject;

    // Default constructor
    public StudentDTO() {}

    // All-args constructor
    public StudentDTO(String name, String email, String phone,
                      LocalDate dateOfBirth, List<String> previousCourses,
                      String appliedSubject) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.previousCourses = previousCourses;
        this.appliedSubject = appliedSubject;
    }

    // Getters and setters
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

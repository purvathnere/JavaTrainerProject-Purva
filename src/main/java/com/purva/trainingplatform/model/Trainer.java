package com.purva.trainingplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ElementCollection
    private List<String> subjectExpertise;

    @Min(1)
    @Max(5)
    private Double rating;

    private Integer yearsOfExperience;

    @ElementCollection
    private List<LocalDate> availabilityPeriods;

    // Default constructor
    public Trainer() {}

    // All-args constructor
    public Trainer(Long id, String name, List<String> subjectExpertise,
                   Double rating, Integer yearsOfExperience, List<LocalDate> availabilityPeriods) {
        this.id = id;
        this.name = name;
        this.subjectExpertise = subjectExpertise;
        this.rating = rating;
        this.yearsOfExperience = yearsOfExperience;
        this.availabilityPeriods = availabilityPeriods;
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

    public List<String> getSubjectExpertise() {
        return subjectExpertise;
    }

    public void setSubjectExpertise(List<String> subjectExpertise) {
        this.subjectExpertise = subjectExpertise;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<LocalDate> getAvailabilityPeriods() {
        return availabilityPeriods;
    }

    public void setAvailabilityPeriods(List<LocalDate> availabilityPeriods) {
        this.availabilityPeriods = availabilityPeriods;
    }
}


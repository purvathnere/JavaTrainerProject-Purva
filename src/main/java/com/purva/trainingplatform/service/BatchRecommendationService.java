package com.purva.trainingplatform.service;

import com.purva.trainingplatform.exception.ResourceNotFoundException;
import com.purva.trainingplatform.model.Batch;
import com.purva.trainingplatform.model.Student;
import com.purva.trainingplatform.repository.BatchRepository;
import com.purva.trainingplatform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BatchRecommendationService {

    private final BatchRepository batchRepository;
    private final StudentRepository studentRepository;

    public BatchRecommendationService(BatchRepository batchRepository, StudentRepository studentRepository) {
        this.batchRepository = batchRepository;
        this.studentRepository = studentRepository;
    }

    public List<Batch> recommendBatchesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

        List<Batch> potentialBatches = batchRepository.findBySubject(student.getAppliedSubject());

        // Filter out batches the student already took
        potentialBatches = potentialBatches.stream()
                .filter(batch -> !student.getPreviousCourses().contains(batch.getSubject()))
                .collect(Collectors.toList());

        // Calculate score for each batch
        Map<Batch, Double> batchScores = new HashMap<>();
        for (Batch batch : potentialBatches) {
            double score = calculateBatchScore(student, batch);
            batchScores.put(batch, score);
        }

        // Sort by score descending
        return batchScores.entrySet().stream()
                .sorted(Map.Entry.<Batch, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double calculateBatchScore(Student student, Batch batch) {
        double score = 0.0;

        // 1. Score for earlier start date (sooner = better)
        long daysUntilStart = ChronoUnit.DAYS.between(LocalDate.now(), batch.getStartDate());
        score += 100.0 / (daysUntilStart + 1); // avoid divide by 0

        // 2. Score for matching past course (less match = better)
        long overlap = student.getPreviousCourses().stream()
                .filter(course -> batch.getSubject().toLowerCase().contains(course.toLowerCase()))
                .count();
        score += overlap * 10;

        return score;
    }
}

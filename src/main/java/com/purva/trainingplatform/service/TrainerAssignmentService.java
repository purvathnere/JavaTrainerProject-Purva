package com.purva.trainingplatform.service;

import com.purva.trainingplatform.exception.ResourceNotFoundException;
import com.purva.trainingplatform.model.Batch;
import com.purva.trainingplatform.model.Trainer;
import com.purva.trainingplatform.repository.BatchRepository;
import com.purva.trainingplatform.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerAssignmentService {

    private final TrainerRepository trainerRepository;
    private final BatchRepository batchRepository;

    public TrainerAssignmentService(TrainerRepository trainerRepository, BatchRepository batchRepository) {
        this.trainerRepository = trainerRepository;
        this.batchRepository = batchRepository;
    }

    public Trainer assignTrainerToBatch(Long batchId) {
        Optional<Batch> batchOptional = batchRepository.findById(batchId);
        if (batchOptional.isEmpty()) {
            throw new ResourceNotFoundException("Batch not found with ID: " + batchId);
        }

        Batch batch = batchOptional.get();
        String subject = batch.getSubject();

        // Step 1: Find trainers who have the required subject
        List<Trainer> eligibleTrainers = trainerRepository.findBySubjectExpertiseContaining(subject);

        // Step 2: Filter out trainers who are unavailable during batch period
        eligibleTrainers = eligibleTrainers.stream()
                .filter(trainer -> isTrainerAvailable(trainer, batch.getStartDate(), batch.getEndDate()))
                .collect(Collectors.toList());

        if (eligibleTrainers.isEmpty()) {
            throw new RuntimeException("No eligible trainers available for subject: " + subject);
        }

        // Step 3: Sort by rating (high to low) and then by workload (low to high)
        eligibleTrainers.sort(Comparator
                .comparingDouble(Trainer::getRating).reversed()
                .thenComparingInt(this::getTrainerWorkload));

        Trainer selectedTrainer = eligibleTrainers.get(0);
        batch.setAssignedTrainer(selectedTrainer);
        batchRepository.save(batch);

        return selectedTrainer;
    }

    private boolean isTrainerAvailable(Trainer trainer, LocalDate start, LocalDate end) {
        return batchRepository.findByAssignedTrainerAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                trainer, end, start).isEmpty();
    }

    private int getTrainerWorkload(Trainer trainer) {
        return batchRepository.countByAssignedTrainer(trainer);
    }
}

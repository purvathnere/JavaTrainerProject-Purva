package com.purva.trainingplatform.service;

import com.purva.trainingplatform.exception.ResourceNotFoundException;
import com.purva.trainingplatform.model.Batch;
import com.purva.trainingplatform.repository.BatchRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchService {

    private final BatchRepository batchRepository;

    // Constructor (no Lombok)
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    // Add a new batch
    public Batch addBatch(@Valid Batch batch) {
        return batchRepository.save(batch);
    }

    // Get a batch by ID
    public Batch getBatchById(Long id) {
        Optional<Batch> optionalBatch = batchRepository.findById(id);
        return optionalBatch.orElseThrow(() -> new ResourceNotFoundException("Batch not found with ID: " + id));
    }

    // Get all batches
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    // Get batches by subject
    public List<Batch> getBatchesBySubject(String subject) {
        return batchRepository.findBySubject(subject);
    }

    // Update an existing batch
    public Batch updateBatch(Long id, @Valid Batch updatedBatch) {
        Batch existingBatch = getBatchById(id);

        existingBatch.setTitle(updatedBatch.getTitle());
        existingBatch.setSubject(updatedBatch.getSubject());
        existingBatch.setMaxCapacity(updatedBatch.getMaxCapacity());
        existingBatch.setStartDate(updatedBatch.getStartDate());
        existingBatch.setEndDate(updatedBatch.getEndDate());
        existingBatch.setAssignedTrainer(updatedBatch.getAssignedTrainer());
        existingBatch.setEnrolledStudents(updatedBatch.getEnrolledStudents());

        return batchRepository.save(existingBatch);
    }

    // Delete a batch
    public void deleteBatch(Long id) {
        if (!batchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Batch not found with ID: " + id);
        }
        batchRepository.deleteById(id);
    }
}


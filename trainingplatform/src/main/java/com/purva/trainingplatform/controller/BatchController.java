package com.purva.trainingplatform.controller;

import com.purva.trainingplatform.model.Batch;
import com.purva.trainingplatform.model.Trainer;
import com.purva.trainingplatform.service.BatchService;
import com.purva.trainingplatform.service.TrainerAssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;
    private final TrainerAssignmentService trainerAssignmentService;

    public BatchController(BatchService batchService,
                           TrainerAssignmentService trainerAssignmentService) {
        this.batchService = batchService;
        this.trainerAssignmentService = trainerAssignmentService;
    }

    @PostMapping
    public ResponseEntity<Batch> addBatch(@Valid @RequestBody Batch batch) {
        return ResponseEntity.ok(batchService.addBatch(batch));
    }

    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() {
        return ResponseEntity.ok(batchService.getAllBatches());
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<Batch>> getBatchesBySubject(@PathVariable String subject) {
        return ResponseEntity.ok(batchService.getBatchesBySubject(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Batch> updateBatch(@PathVariable Long id,
                                             @Valid @RequestBody Batch batch) {
        return ResponseEntity.ok(batchService.updateBatch(id, batch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{batchId}/assign-trainer")
    public ResponseEntity<Trainer> assignTrainer(@PathVariable Long batchId) {
        return ResponseEntity.ok(trainerAssignmentService.assignTrainerToBatch(batchId));
    }
}

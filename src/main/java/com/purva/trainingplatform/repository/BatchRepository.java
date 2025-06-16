package com.purva.trainingplatform.repository;

import com.purva.trainingplatform.model.Batch;
import com.purva.trainingplatform.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    List<Batch> findBySubject(String subject);

    List<Batch> findByAssignedTrainerAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        Trainer trainer, LocalDate end, LocalDate start
    );

    int countByAssignedTrainer(Trainer trainer);
}

package com.purva.trainingplatform.repository;

import com.purva.trainingplatform.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    List<Trainer> findBySubjectExpertiseContaining(String subject);
}


package com.purva.trainingplatform.repository;

import com.purva.trainingplatform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

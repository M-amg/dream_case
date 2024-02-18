package com.dreamcase.app.repositories;

import com.dreamcase.app.entities.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}

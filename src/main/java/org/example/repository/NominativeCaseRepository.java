package org.example.repository;

import org.example.entity.NominativeCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NominativeCaseRepository extends JpaRepository<NominativeCase, Long> {
    NominativeCase findBySingular(String singular);
}

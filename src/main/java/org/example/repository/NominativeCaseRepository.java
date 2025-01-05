package org.example.repository;

import org.example.entity.NominativeCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NominativeCaseRepository extends JpaRepository<NominativeCase, Long> {
    NominativeCase findBySingular(String singular);

    @Query(value = "SELECT DISTINCT ON (id) * FROM nominative_case ORDER BY id, RANDOM() LIMIT :count", nativeQuery = true)
    List<NominativeCase> findFixedAmountOfNominativeCases(@Param("count") int count);
}

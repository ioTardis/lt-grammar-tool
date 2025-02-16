package org.example.repository;

import org.example.entity.GenetiveCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenitiveCaseRepository extends JpaRepository<GenetiveCase, Long> {
    GenetiveCase findBySingular(String singular);

    @Query(value = "SELECT DISTINCT ON (id) * FROM genitive_case ORDER BY id, RANDOM() LIMIT :count", nativeQuery = true)
    List<GenetiveCase> findFixedAmountOfGenitiveCases(@Param("count") int count);
}

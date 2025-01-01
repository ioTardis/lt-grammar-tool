package org.example.service;

import org.example.entity.NominativeCase;
import org.example.repository.NominativeCaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class NominativeCaseServiceTest {

    @Autowired
    private NominativeCaseRepository repository;

    private NominativeCase nominativeCase;

    @BeforeEach
    public void setUp() {
        nominativeCase = NominativeCase.builder()
                .singular("vyras")
                .plural("vyrai")
                .build();
    }

    @Test
    public void testSaveNominativeCase() {
        NominativeCase savedCase = repository.save(nominativeCase);
        assertNotNull(savedCase);
        assertEquals(nominativeCase, savedCase);
    }

    @Test
    public void testGetAllNominativeCases() {
        repository.save(nominativeCase);
        List<NominativeCase> cases = repository.findAll();
        assertNotNull(cases);
        assertEquals(1, cases.size());
    }

    @Test
    public void testGetNominativeCaseBySingular() {
        repository.save(nominativeCase);
        NominativeCase foundCase = repository.findBySingular("vyras");
        assertNotNull(foundCase);
        assertEquals(nominativeCase, foundCase);
    }
}
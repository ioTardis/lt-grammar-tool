package org.example.service;

import org.example.entity.NominativeCase;
import org.example.repository.NominativeCaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void shouldSaveNominativeCase() {
        // Given - When
        final var savedCase = repository.save(nominativeCase);

        // Then
        assertNotNull(savedCase);
        assertEquals(nominativeCase, savedCase);
    }

    @Test
    public void shouldGetAllNominativeCases() {
        // Given
        repository.save(nominativeCase);

        // When
        final var result = repository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void shouldGetNominativeCaseBySingular() {
        // Given
        repository.save(nominativeCase);

        // When
        final var result = repository.findBySingular("vyras");

        // Then
        assertNotNull(result);
        assertEquals(nominativeCase, result);
    }

    @ParameterizedTest
    @MethodSource("provideMultipleNominativeCases")
    public void shouldGetFixedAmountOfNominativeCases(List<NominativeCase> nominativeCases) {
        // Given
        nominativeCases.forEach(repository::save);

        // When
        final List<NominativeCase> result = repository.findFixedAmountOfNominativeCases(nominativeCases.size());

        // Then
        assertNotNull(result);
        assertThat(nominativeCases).hasSameSizeAs(result);
        assertThat(nominativeCases).hasSameElementsAs(result);
    }

    static private Stream<Arguments> provideMultipleNominativeCases() {
        return Stream.of(
                Arguments.of(List.of(
                        mockNominativeCase("vyras", "vyrai"),
                        mockNominativeCase("moteris", "moterys"),
                        mockNominativeCase("vaikas", "vaikai")
                )),
                Arguments.of(List.of(
                        mockNominativeCase("vaikas", "vaikai"),
                        mockNominativeCase("moteris", "moterys")
                )),
                Arguments.of(List.of(
                        mockNominativeCase("namas", "namai"),
                        mockNominativeCase("automobilis", "automobiliai"),
                        mockNominativeCase("katinas", "katinai"),
                        mockNominativeCase("surelis", "sureliai")
                ))
        );
    }

    static private NominativeCase mockNominativeCase(String singular, String plural) {
        return NominativeCase.builder()
                .singular(singular)
                .plural(plural)
                .build();
    }
}
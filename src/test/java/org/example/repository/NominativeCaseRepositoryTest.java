package org.example.repository;

import org.example.entity.NominativeCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.TestData.getNominativeCase;
import static org.example.TestData.mockNominativeCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class NominativeCaseRepositoryTest {

    @Autowired
    NominativeCaseRepository repository;

    private final NominativeCase expectedNominativeCase = getNominativeCase();

    @Test
    public void shouldSaveNominativeCase() {
        // Given - When
        final var savedCase = repository.save(expectedNominativeCase);

        // Then
        assertNotNull(savedCase);
        assertEquals(expectedNominativeCase, savedCase);
    }

    @Test
    public void shouldGetAllNominativeCases() {
        // Given
        repository.save(expectedNominativeCase);

        // When
        final var result = repository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void shouldGetNominativeCaseBySingular() {
        // Given
        repository.save(expectedNominativeCase);

        // When
        final var result = repository.findBySingular("vyras");

        // Then
        assertNotNull(result);
        assertEquals(expectedNominativeCase, result);
    }

    @Disabled("RANDOM() PostgreSQL function in H2 is RAND(). Need special configuration or use of test container")
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
}
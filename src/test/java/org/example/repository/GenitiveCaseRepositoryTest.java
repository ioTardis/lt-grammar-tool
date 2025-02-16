package org.example.repository;

import org.example.entity.GenetiveCase;
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
import static org.example.TestData.getGenitiveCase;
import static org.example.TestData.mockGenitiveCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class GenitiveCaseRepositoryTest {

    @Autowired
    GenitiveCaseRepository repository;

    private final GenetiveCase expectedGenitiveCase = getGenitiveCase();

    @Test
    public void shouldSaveGenitiveCase() {
        // Given - When
        final var savedCase = repository.save(expectedGenitiveCase);

        // Then
        assertNotNull(savedCase);
        assertEquals(expectedGenitiveCase, savedCase);
    }

    @Test
    public void shouldGetAllGenitiveCases() {
        // Given
        repository.save(expectedGenitiveCase);

        // When
        final var result = repository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void shouldGetGenitiveCaseBySingular() {
        // Given
        repository.save(expectedGenitiveCase);

        // When
        final var result = repository.findBySingular("vyro");

        // Then
        assertNotNull(result);
        assertEquals(expectedGenitiveCase, result);
    }

    @Disabled("RANDOM() PostgreSQL function in H2 is RAND(). Need special configuration or use of test container")
    @ParameterizedTest
    @MethodSource("provideFixedAmountOfGenitiveCases")
    public void shouldGetFixedAmountOfGenitiveCases(List<GenetiveCase> genitiveCases, int count) {
        // Given
        repository.save(expectedGenitiveCase);

        // When
        final var result = repository.findFixedAmountOfGenitiveCases(count);

        // Then
        assertNotNull(result);
        assertThat(genitiveCases).hasSameSizeAs(result);
        assertThat(genitiveCases).hasSameElementsAs(result);
    }

    private static Stream<Arguments> provideFixedAmountOfGenitiveCases() {
        return Stream.of(
                Arguments.of(
                        mockGenitiveCase("vyro", "vyrų"),
                        mockGenitiveCase("moterio", "moterių"),
                        mockGenitiveCase("vaiko", "vaikų")
                ),
                Arguments.of(
                        mockGenitiveCase("vyro", "vyrų"),
                        mockGenitiveCase("moterio", "moterių")
                ),
                Arguments.of(
                        mockGenitiveCase("namo", "namų"),
                        mockGenitiveCase("automobilio", "automobilių"),
                        mockGenitiveCase("knygos", "knygų"),
                        mockGenitiveCase("surelio", "surelių")
                )
        );
    }
}
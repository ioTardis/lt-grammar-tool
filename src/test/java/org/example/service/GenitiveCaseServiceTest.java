package org.example.service;

import org.example.entity.GenetiveCase;
import org.example.repository.GenitiveCaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

import static org.example.TestData.getGenitiveCase;
import static org.example.TestData.mockGenitiveCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {GenitiveCaseService.class})
class GenitiveCaseServiceTest {

    @Autowired
    private GenitiveCaseService genitiveCaseService;

    @MockBean
    private GenitiveCaseRepository genitiveCaseRepository;

    private final GenetiveCase expectedGenitiveCase = getGenitiveCase();

    @Test
    void whenSaveAndInsertSuccessful_thenReturnCase() {
        // Given
        when(genitiveCaseRepository.save(expectedGenitiveCase)).thenReturn(expectedGenitiveCase);

        // When
        final var result = genitiveCaseService.save(expectedGenitiveCase);

        // Then
        assertEquals(expectedGenitiveCase, result);
    }

    @Test
    void whenSaveAndInsertUnsuccessful_thenThrowException() {
        // Given
        when(genitiveCaseRepository.save(expectedGenitiveCase)).thenThrow(DuplicateKeyException.class);

        // When - Then
        assertThrows(DuplicateKeyException.class, () -> genitiveCaseService.save(expectedGenitiveCase));
    }

    @Test
    void whenGetAllAndRepositoryReturnsCases_thenReturnCases() {
        // Given
        final var expectedCases = List.of(mockGenitiveCase("vyras", "vyro"), mockGenitiveCase("moteris", "moters"));
        when(genitiveCaseRepository.findAll()).thenReturn(expectedCases);

        // When
        final var result = genitiveCaseService.getAll();

        // Then
        assertEquals(expectedCases, result);
    }

    @Test
    void whenGetAllAndRepositoryReturnsEmptyList_thenReturnEmptyList() {
        // Given
        when(genitiveCaseRepository.findAll()).thenReturn(List.of());

        // When
        final var result = genitiveCaseService.getAll();

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void whenGetBySingularAndRepositoryReturnsCase_thenReturnOptionalCase() {
        // Given
        when(genitiveCaseRepository.findBySingular(expectedGenitiveCase.getSingular())).thenReturn(expectedGenitiveCase);

        // When
        final var result = genitiveCaseService.getBySingular(expectedGenitiveCase.getSingular());

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedGenitiveCase, result.get());
    }

    @Test
    void whenGetBySingularAndRepositoryReturnsNull_thenReturnEmptyOptional() {
        // Given
        when(genitiveCaseRepository.findBySingular(expectedGenitiveCase.getSingular())).thenReturn(null);

        // When
        final var result = genitiveCaseService.getBySingular(expectedGenitiveCase.getSingular());

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void whenGetBySingularAndRepositoryReturnsEmptyOptional_thenReturnEmptyOptional() {
        // Given
        when(genitiveCaseRepository.findBySingular(expectedGenitiveCase.getSingular())).thenReturn(null);

        // When
        final var result = genitiveCaseService.getBySingular(expectedGenitiveCase.getSingular());

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void whenGetFixedAmountAndRepositoryReturnsCases_thenReturnCases() {
        // Given
        final var expectedCases = List.of(mockGenitiveCase("vyras", "vyro"), mockGenitiveCase("moteris", "moters"));
        when(genitiveCaseRepository.findFixedAmountOfGenitiveCases(2)).thenReturn(expectedCases);

        // When
        final var result = genitiveCaseService.getFixedAmount(2);

        // Then
        assertEquals(expectedCases, result);
    }

    @Test
    void whenGetFixedAmountAndRepositoryReturnsEmptyList_thenReturnEmptyList() {
        // Given
        when(genitiveCaseRepository.findFixedAmountOfGenitiveCases(2)).thenReturn(List.of());

        // When
        final var result = genitiveCaseService.getFixedAmount(2);

        // Then
        assertEquals(List.of(), result);
    }
}
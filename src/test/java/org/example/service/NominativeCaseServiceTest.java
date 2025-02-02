package org.example.service;

import org.example.entity.NominativeCase;
import org.example.repository.NominativeCaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

import static org.example.TestData.getNominativeCase;
import static org.example.TestData.mockNominativeCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {NominativeCaseService.class})
class NominativeCaseServiceTest {

    @Autowired
    private NominativeCaseService nominativeCaseService;

    @MockBean
    private NominativeCaseRepository nominativeCaseRepository;

    private final NominativeCase expectedNominativeCase = getNominativeCase();

    @Test
    void whenSaveAndInsertSuccessful_thenReturnCase() {
        // Given
        when(nominativeCaseRepository.save(expectedNominativeCase)).thenReturn(expectedNominativeCase);

        // When
        final var result = nominativeCaseService.save(expectedNominativeCase);

        // Then
        assertEquals(expectedNominativeCase, result);
    }

    @Test
    void whenSaveAndInsertUnsuccessful_thenThrowException() {
        // Given
        when(nominativeCaseRepository.save(expectedNominativeCase)).thenThrow(DuplicateKeyException.class);

        // When - Then
        assertThrows(DuplicateKeyException.class, () -> nominativeCaseService.save(expectedNominativeCase));
    }

    @Test
    void whenGetAllAndRepositoryReturnsCases_thenReturnCases() {
        // Given
        final var expectedCases = List.of(mockNominativeCase("vyras", "vyrai"), mockNominativeCase("moteris", "moterys"));
        when(nominativeCaseRepository.findAll()).thenReturn(expectedCases);

        // When
        final var result = nominativeCaseService.getAll();

        // Then
        assertEquals(expectedCases, result);
    }

    @Test
    void whenGetAllAndRepositoryReturnsEmptyList_thenReturnEmptyList() {
        // Given
        when(nominativeCaseRepository.findAll()).thenReturn(List.of());

        // When
        final var result = nominativeCaseService.getAll();

        // Then
        assertEquals(List.of(), result);
    }

    @Test
    void whenGetBySingularAndRepositoryReturnsCase_thenReturnOptionalCase() {
        // Given
        final var singular = "vyras";
        final var expectedCase = getNominativeCase();
        when(nominativeCaseRepository.findBySingular(singular)).thenReturn(expectedCase);

        // When
        final var result = nominativeCaseService.getBySingular(singular);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedCase, result.get());
    }

    @Test
    void whenGetBySingularAndRepositoryReturnsNull_thenReturnEmptyOptional() {
        // Given
        final var singular = "vyras";
        when(nominativeCaseRepository.findBySingular(singular)).thenReturn(null);

        // When
        final var result = nominativeCaseService.getBySingular(singular);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void whenGetFixedAmountAndRepositoryReturnsCases_thenReturnCases() {
        // Given
        final var expectedCases = List.of(mockNominativeCase("vyras", "vyrai"), mockNominativeCase("moteris", "moterys"));
        when(nominativeCaseRepository.findFixedAmountOfNominativeCases(2)).thenReturn(expectedCases);

        // When
        final var result = nominativeCaseService.getFixedAmount(2);

        // Then
        assertEquals(expectedCases, result);
    }

    @Test
    void whenGetFixedAmountAndRepositoryReturnsEmptyList_thenReturnEmptyList() {
        // Given
        when(nominativeCaseRepository.findFixedAmountOfNominativeCases(2)).thenReturn(List.of());

        // When
        final var result = nominativeCaseService.getFixedAmount(2);

        // Then
        assertEquals(List.of(), result);
    }
}
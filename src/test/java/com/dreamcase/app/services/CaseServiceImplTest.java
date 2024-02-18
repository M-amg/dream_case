package com.dreamcase.app.services;

import com.dreamcase.app.dto.CaseDto;
import com.dreamcase.app.entities.Case;
import com.dreamcase.app.exceptions.CaseNotFoundException;
import com.dreamcase.app.mappers.CaseMapper;
import com.dreamcase.app.repositories.CaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CaseServiceImplTest {
    @Mock
    private CaseRepository caseRepository;

    @Mock
    private CaseMapper caseMapper;

    @InjectMocks
    private CaseServiceImpl caseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testReadCase() {
        Long caseId = 1L;
        Case mockCase = new Case();
        mockCase.setId(caseId);
        CaseDto mockDto = new CaseDto();
        mockDto.setId(caseId);

        when(caseRepository.findById(caseId)).thenReturn(Optional.of(mockCase));
        when(caseMapper.toDto(mockCase)).thenReturn(mockDto);

        CaseDto resultDto = caseService.readCase(caseId);

        assertEquals(caseId, resultDto.getId());
        verify(caseRepository, times(1)).findById(caseId);
        verify(caseMapper, times(1)).toDto(mockCase);
    }

    @Test
    void testReadCase_ThrowsException() {
        Long caseId = 1L;

        when(caseRepository.findById(caseId)).thenReturn(Optional.empty());

        assertThrows(CaseNotFoundException.class, () -> caseService.readCase(caseId));

        verify(caseRepository, times(1)).findById(caseId);
        verifyNoInteractions(caseMapper);
    }

    @Test
    void testCreateCase() {
        CaseDto caseDto = new CaseDto();
        Case newCase = new Case();
        CaseDto savedDto = new CaseDto();
        savedDto.setId(1L);

        when(caseMapper.toEntity(caseDto)).thenReturn(newCase);
        when(caseRepository.save(newCase)).thenReturn(newCase);
        when(caseMapper.toDto(newCase)).thenReturn(savedDto);

        CaseDto resultDto = caseService.createCase(caseDto);

        assertEquals(savedDto.getId(), resultDto.getId());
        verify(caseMapper, times(1)).toEntity(caseDto);
        verify(caseRepository, times(1)).save(newCase);
        verify(caseMapper, times(1)).toDto(newCase);
    }

    @Test
    void testUpdateCase() {
        Long caseId = 1L;
        CaseDto caseDto = new CaseDto();
        caseDto.setId(caseId);
        Case existingCase = new Case();
        Case updatedCase = new Case();
        updatedCase.setId(caseId);
        CaseDto updatedDto = new CaseDto();
        updatedDto.setId(caseId);

        when(caseRepository.findById(caseId)).thenReturn(java.util.Optional.of(existingCase));
        when(caseRepository.save(existingCase)).thenReturn(updatedCase);
        when(caseMapper.toDto(updatedCase)).thenReturn(updatedDto);

        CaseDto resultDto = caseService.updateCase(caseDto, caseId);

        assertEquals(updatedDto.getId(), resultDto.getId());
        verify(caseRepository, times(1)).findById(caseId);
        verify(caseRepository, times(1)).save(existingCase);
        verify(caseMapper, times(1)).toDto(updatedCase);
    }

    @Test
    void testUpdateCase_ThrowsException() {
        Long caseId = 1L;
        CaseDto caseDto = new CaseDto();

        when(caseRepository.findById(caseId)).thenReturn(java.util.Optional.empty());

        assertThrows(CaseNotFoundException.class, () -> caseService.updateCase(caseDto, caseId));

        verify(caseRepository, times(1)).findById(caseId);
        verifyNoMoreInteractions(caseRepository, caseMapper);
    }

    @Test
    void testDeleteCase() {
        Long caseId = 1L;
        Case existingCase = new Case();

        when(caseRepository.findById(caseId)).thenReturn(java.util.Optional.of(existingCase));

        caseService.deleteCase(caseId);

        verify(caseRepository, times(1)).findById(caseId);
        verify(caseRepository, times(1)).delete(existingCase);
    }

    @Test
    void testDeleteCase_ThrowsException() {
        Long caseId = 1L;

        when(caseRepository.findById(caseId)).thenReturn(java.util.Optional.empty());

        assertThrows(CaseNotFoundException.class, () -> caseService.deleteCase(caseId));

        verify(caseRepository, times(1)).findById(caseId);
        verifyNoMoreInteractions(caseRepository);
    }
}
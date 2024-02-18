package com.dreamcase.app.services;

import com.dreamcase.app.dto.CaseDto;
import com.dreamcase.app.entities.Case;
import com.dreamcase.app.enums.CaseExceptionMessage;
import com.dreamcase.app.exceptions.CaseNotFoundException;
import com.dreamcase.app.mappers.CaseMapper;
import com.dreamcase.app.repositories.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;

    @Override
    public CaseDto readCase(Long caseId) {
        Case existingCase = caseRepository.findById(caseId).orElseThrow(
                () -> new CaseNotFoundException(CaseExceptionMessage.CASE_NOT_FOUND_EXCEPTION.getValue() + caseId)
        );
        return caseMapper.toDto(existingCase);
    }

    @Override
    public CaseDto createCase(CaseDto caseDto) {
        Case newCase = caseMapper.toEntity(caseDto);
        return caseMapper.toDto(caseRepository.save(newCase));
    }

    @Override
    public CaseDto updateCase(CaseDto caseDto, Long caseId) {
        Case existingCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new CaseNotFoundException(CaseExceptionMessage.CASE_NOT_FOUND_EXCEPTION.getValue()  + caseId));
        existingCase.setTitle(caseDto.getTitle());
        existingCase.setDescription(caseDto.getDescription());
        return caseMapper.toDto(caseRepository.save(existingCase));
    }

    @Override
    public void deleteCase(Long caseId) {
        Case existingCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new CaseNotFoundException("No case found with ID " + caseId));
        caseRepository.delete(existingCase);
    }
}

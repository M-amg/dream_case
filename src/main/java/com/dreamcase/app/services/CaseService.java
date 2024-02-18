package com.dreamcase.app.services;

import com.dreamcase.app.dto.CaseDto;

public interface CaseService {
    public CaseDto readCase(Long caseId);
    public CaseDto createCase(CaseDto caseDto);
    public CaseDto updateCase(CaseDto caseDto,Long caseId);
    public void deleteCase(Long caseId);
}

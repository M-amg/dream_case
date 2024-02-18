package com.dreamcase.app.controllers;

import com.dreamcase.app.api.CasesApi;
import com.dreamcase.app.dto.CaseDto;
import com.dreamcase.app.services.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CaseController implements CasesApi {

    private final CaseService caseService;

    @Override
    public ResponseEntity<CaseDto> createCase(CaseDto caseDto) {
        return new ResponseEntity<>(caseService.createCase(caseDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCase(Long caseID) {
        caseService.deleteCase(caseID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CaseDto> readCase(Long caseID) {
        return new ResponseEntity<>(caseService.readCase(caseID), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CaseDto> updateCase(Long caseID, CaseDto caseDto) {
        return new ResponseEntity<>(caseService.updateCase(caseDto, caseID), HttpStatus.OK);
    }
}

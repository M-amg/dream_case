package com.dreamcase.app.services;

import com.dreamcase.app.dto.CaseDto;
import com.dreamcase.app.entities.Case;
import com.dreamcase.app.exceptions.CaseNotFoundException;
import com.dreamcase.app.mappers.CaseMapper;
import com.dreamcase.app.repositories.CaseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest(
        properties = {
                "logging.level.org.springframework=OFF",
                "logging.level.root=OFF",
                "spring.main.lazy-initialization=true",
                "spring.liquibase.enabled=true",
                "spring.jpa.hibernate.ddl-auto=none",

        })
class CaseServiceImplIntegrationTest {

    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private CaseMapper caseMapper;
    private CaseService caseService;

    @BeforeEach
    public void setUp() {
        caseService = new CaseServiceImpl(caseRepository, caseMapper);
    }

    @AfterEach
    public void tearDown() {
        caseRepository.deleteAll();
    }

    @Test
    void testCreateCase() {
        CaseDto caseDto = getCaseDto("Test Case", "Test Description");

        CaseDto createdCase = caseService.createCase(caseDto);

        assertEquals(caseDto.getTitle(), createdCase.getTitle());
        assertEquals(caseDto.getDescription(), createdCase.getDescription());
    }


    @Test
    void testReadCase() {
        Case testCase = getaCase();

        CaseDto retrievedCase = caseService.readCase(testCase.getId());

        assertEquals(testCase.getTitle(), retrievedCase.getTitle());
        assertEquals(testCase.getDescription(), retrievedCase.getDescription());
    }


    @Test
    void testUpdateCase() {
        Case testCase = getaCase();

        CaseDto caseDto = getCaseDto("Updated Test Case", "Updated Test Description");

        CaseDto updatedCase = caseService.updateCase(caseDto, testCase.getId());

        assertEquals(caseDto.getTitle(), updatedCase.getTitle());
        assertEquals(caseDto.getDescription(), updatedCase.getDescription());
    }

    @Test
    void testDeleteCase() {
        Case testCase = getaCase();
        Long caseId = testCase.getId();

        caseService.deleteCase(caseId);

        assertThrows(CaseNotFoundException.class, () -> caseService.readCase(caseId));
    }


    private Case getaCase() {
        Case testCase = new Case();
        testCase.setTitle("Test Case");
        testCase.setDescription("Test Description");
        testCase = caseRepository.save(testCase);
        return testCase;
    }

    private CaseDto getCaseDto(String title, String description) {
        CaseDto caseDto = new CaseDto();
        caseDto.setTitle(title);
        caseDto.setDescription(description);
        return caseDto;
    }
}
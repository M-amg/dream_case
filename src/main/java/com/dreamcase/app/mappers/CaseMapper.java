package com.dreamcase.app.mappers;

import com.dreamcase.app.dto.CaseDto;
import com.dreamcase.app.entities.Case;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {
        DateMapper.class
})
public interface CaseMapper {


    CaseDto toDto(Case sourceCase);

    Case toEntity(CaseDto caseDto);
}

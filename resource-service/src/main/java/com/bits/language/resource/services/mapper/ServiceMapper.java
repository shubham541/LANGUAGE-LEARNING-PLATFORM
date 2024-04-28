package com.bits.language.resource.services.mapper;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.model.Language;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    LanguageDTO mapToDTO(Language language);
}

package com.bits.language.resource.services.mapper;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.dto.LanguageGrammarDTO;
import com.bits.language.resource.dto.LanguageVocabularyDTO;
import com.bits.language.resource.model.Language;
import com.bits.language.resource.model.LanguageGrammar;
import com.bits.language.resource.model.LanguageVocabulary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    LanguageDTO mapToDTO(Language language);

    LanguageGrammarDTO mapLanguageGrammarToDto(LanguageGrammar languageGrammar);

    LanguageVocabularyDTO mapLanguageVocabularyToDto(LanguageVocabulary languageVocabulary);
}


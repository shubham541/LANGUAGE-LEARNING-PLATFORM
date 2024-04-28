package com.bits.language.resource.services.impl;

import com.bits.language.resource.dto.LanguageLearningMaterial;
import com.bits.language.resource.model.LanguageGrammar;
import com.bits.language.resource.model.LanguageVocabulary;
import com.bits.language.resource.repository.LanguageGrammarRepo;
import com.bits.language.resource.repository.LanguageVocabularyRepo;
import com.bits.language.resource.services.LearningMaterialService;
import com.bits.language.resource.services.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningMaterialServiceImpl implements LearningMaterialService {

	private final LanguageGrammarRepo languageGrammarRepo;

	private final LanguageVocabularyRepo languageVocabularyRepo;

	private final ServiceMapper serviceMapper;

	@Override
	public LanguageLearningMaterial getLearningMaterialsByLanguage(String languageCd) {
		LanguageGrammar languageGrammar = languageGrammarRepo.findByCode(languageCd)
				.orElseThrow();
		LanguageVocabulary languageVocabulary = languageVocabularyRepo.findByCode(languageCd)
				.orElseThrow();
		return new LanguageLearningMaterial(
				serviceMapper.mapLanguageVocabularyToDto(languageVocabulary),
				serviceMapper.mapLanguageGrammarToDto(languageGrammar)
		);
	}

}

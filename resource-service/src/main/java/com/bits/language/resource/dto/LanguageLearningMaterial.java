package com.bits.language.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageLearningMaterial {

    private LanguageVocabularyDTO languageVocabulary;

    private LanguageGrammarDTO languageGrammar;

}

// LanguageVocabularyDTO.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.bits.language.resource.dto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LanguageVocabularyDTO {
    private Map<String, Object> Numbers;
    private List<String> prepositions;
    private String code;
    private Map<String, Object> Nouns;
    private Map<String, Object> Determiners;
    private List<String> interjections;
    private Map<String, Object> Adverbs;
    private List<String> conjunctions;
    private Map<String, Object> Adjectives;
    private Map<String, Object> Pronouns;
    private Map<String, Object> Verbs;
}

// LanguageVocabulary.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.bits.language.resource.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "language_vocabulary")
public class LanguageVocabulary {
    @Id
    private String id;
    private Map<String, Object> Numbers;
    private List<String> Prepositions;
    private String code;
    private Map<String, Object> Nouns;
    private Map<String, Object> Determiners;
    private List<String> Interjections;
    private Map<String, Object> Adverbs;
    private List<String> Conjunctions;
    private Map<String, Object> Adjectives;
    private Map<String, Object> Pronouns;
    private Map<String, Object> Verbs;

}

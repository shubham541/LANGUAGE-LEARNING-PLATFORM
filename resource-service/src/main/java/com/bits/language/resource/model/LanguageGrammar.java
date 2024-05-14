// LanguageGrammar.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.bits.language.resource.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "language_grammar_expalinations")
public class LanguageGrammar {
    @Id
    private String id;
    private Map<String, Object> Parts_of_speech;
    private String code;
    private Map<String, Object> Sentence_structure;
    private Map<String, Object> Verb_tenses;

}

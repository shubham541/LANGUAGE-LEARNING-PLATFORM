// LanguageGrammarDTO.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.bits.language.resource.dto;
import lombok.Data;

import java.util.Map;

@Data
public class LanguageGrammarDTO {
    private String id;
    private Map<String, Object> Parts_of_speech;
    private String code;
    private Map<String, Object> Sentence_structure;
    private Map<String, Object> Verb_tenses;

}

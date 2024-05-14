package com.bits.language.resource.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "languages")
public class Language {

    private String code;

    private String name;

    private String desc;
}

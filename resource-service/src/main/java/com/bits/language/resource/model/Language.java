package com.bits.language.resource.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Language {

    private Long code;

    private String name;

    private String desc;
}

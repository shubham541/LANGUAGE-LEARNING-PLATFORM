package com.bits.language.resource.services.mapper;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.model.Language;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T16:40:43+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public LanguageDTO mapToDTO(Language language) {
        if ( language == null ) {
            return null;
        }

        LanguageDTO languageDTO = new LanguageDTO();

        languageDTO.setName( language.getName() );
        if ( language.getCode() != null ) {
            languageDTO.setCode( String.valueOf( language.getCode() ) );
        }
        languageDTO.setDesc( language.getDesc() );

        return languageDTO;
    }
}

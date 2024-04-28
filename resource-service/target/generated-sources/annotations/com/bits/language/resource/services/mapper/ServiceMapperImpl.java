package com.bits.language.resource.services.mapper;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.dto.LanguageGrammarDTO;
import com.bits.language.resource.dto.LanguageVocabularyDTO;
import com.bits.language.resource.model.Language;
import com.bits.language.resource.model.LanguageGrammar;
import com.bits.language.resource.model.LanguageVocabulary;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T20:21:01+0530",
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
        languageDTO.setCode( language.getCode() );
        languageDTO.setDesc( language.getDesc() );

        return languageDTO;
    }

    @Override
    public LanguageGrammarDTO mapLanguageGrammarToDto(LanguageGrammar languageGrammar) {
        if ( languageGrammar == null ) {
            return null;
        }

        LanguageGrammarDTO languageGrammarDTO = new LanguageGrammarDTO();

        languageGrammarDTO.setId( languageGrammar.getId() );
        Map<String, Object> map = languageGrammar.getParts_of_speech();
        if ( map != null ) {
            languageGrammarDTO.setParts_of_speech( new LinkedHashMap<String, Object>( map ) );
        }
        languageGrammarDTO.setCode( languageGrammar.getCode() );
        Map<String, Object> map1 = languageGrammar.getSentence_structure();
        if ( map1 != null ) {
            languageGrammarDTO.setSentence_structure( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = languageGrammar.getVerb_tenses();
        if ( map2 != null ) {
            languageGrammarDTO.setVerb_tenses( new LinkedHashMap<String, Object>( map2 ) );
        }

        return languageGrammarDTO;
    }

    @Override
    public LanguageVocabularyDTO mapLanguageVocabularyToDto(LanguageVocabulary languageVocabulary) {
        if ( languageVocabulary == null ) {
            return null;
        }

        LanguageVocabularyDTO languageVocabularyDTO = new LanguageVocabularyDTO();

        Map<String, Object> map = languageVocabulary.getNumbers();
        if ( map != null ) {
            languageVocabularyDTO.setNumbers( new LinkedHashMap<String, Object>( map ) );
        }
        List<String> list = languageVocabulary.getPrepositions();
        if ( list != null ) {
            languageVocabularyDTO.setPrepositions( new ArrayList<String>( list ) );
        }
        languageVocabularyDTO.setCode( languageVocabulary.getCode() );
        Map<String, Object> map1 = languageVocabulary.getNouns();
        if ( map1 != null ) {
            languageVocabularyDTO.setNouns( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = languageVocabulary.getDeterminers();
        if ( map2 != null ) {
            languageVocabularyDTO.setDeterminers( new LinkedHashMap<String, Object>( map2 ) );
        }
        List<String> list1 = languageVocabulary.getInterjections();
        if ( list1 != null ) {
            languageVocabularyDTO.setInterjections( new ArrayList<String>( list1 ) );
        }
        Map<String, Object> map3 = languageVocabulary.getAdverbs();
        if ( map3 != null ) {
            languageVocabularyDTO.setAdverbs( new LinkedHashMap<String, Object>( map3 ) );
        }
        List<String> list2 = languageVocabulary.getConjunctions();
        if ( list2 != null ) {
            languageVocabularyDTO.setConjunctions( new ArrayList<String>( list2 ) );
        }
        Map<String, Object> map4 = languageVocabulary.getAdjectives();
        if ( map4 != null ) {
            languageVocabularyDTO.setAdjectives( new LinkedHashMap<String, Object>( map4 ) );
        }
        Map<String, Object> map5 = languageVocabulary.getPronouns();
        if ( map5 != null ) {
            languageVocabularyDTO.setPronouns( new LinkedHashMap<String, Object>( map5 ) );
        }
        Map<String, Object> map6 = languageVocabulary.getVerbs();
        if ( map6 != null ) {
            languageVocabularyDTO.setVerbs( new LinkedHashMap<String, Object>( map6 ) );
        }

        return languageVocabularyDTO;
    }
}

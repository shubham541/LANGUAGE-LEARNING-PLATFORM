package com.bits.language.resource.repository;

import com.bits.language.resource.model.LanguageVocabulary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LanguageVocabularyRepo extends MongoRepository<LanguageVocabulary, String>{

    Optional<LanguageVocabulary> findByCode(String languageCd);
}

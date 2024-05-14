package com.bits.language.resource.repository;

import com.bits.language.resource.model.LanguageGrammar;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LanguageGrammarRepo extends MongoRepository<LanguageGrammar, String>{



    Optional<LanguageGrammar> findByCode(String languageCd);

}

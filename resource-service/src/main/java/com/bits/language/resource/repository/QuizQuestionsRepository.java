package com.bits.language.resource.repository;

import com.bits.language.resource.model.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionsRepository extends MongoRepository<Questions, Long> {
	
	List<Questions> findByLanguage(String language);

	@Query("{_id: {$in: ?0}}")
	List<Questions> findAllByIds(List<String> ids);
}

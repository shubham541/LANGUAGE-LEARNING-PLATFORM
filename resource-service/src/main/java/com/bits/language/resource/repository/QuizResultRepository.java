package com.bits.language.resource.repository;

import com.bits.language.resource.model.QuestionResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends MongoRepository<QuestionResult, Long> {

    List<QuestionResult> findByUserName(String userName);

}

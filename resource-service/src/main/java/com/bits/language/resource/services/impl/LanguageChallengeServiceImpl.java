package com.bits.language.resource.services.impl;

import com.bits.language.commons.config.AuthenticationFacade;
import com.bits.language.resource.dto.QuestionsDTO;
import com.bits.language.resource.model.QuestionResult;
import com.bits.language.resource.repository.QuizQuestionsRepository;
import com.bits.language.resource.repository.QuizResultRepository;
import com.bits.language.resource.services.LanguageChallengeService;
import com.bits.language.resource.services.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class LanguageChallengeServiceImpl implements LanguageChallengeService {


	private final QuizQuestionsRepository repository;

	private final QuizResultRepository quizResultRepository;

	private final AuthenticationFacade facade;

	private final ServiceMapper mapper;

	@Override
	public List<QuestionsDTO> findByLanguage(String language) {
		return repository.findByLanguage(language).stream().map(mapper::mapToDTO).toList();
	}

	@Override
	public void submitQuiz(String id, boolean result) {
		String currentUser = facade.getCurrentUser();
		QuestionResult questionResult = new QuestionResult();
		questionResult.setUserName(currentUser);
		questionResult.setId(id);
		questionResult.setCorrect(result);
		quizResultRepository.save(questionResult);
	}

	@Override
	public List<QuestionsDTO> findByUsername(String username) {
		List<QuestionResult> results = quizResultRepository.findByUserName(username);
		List<String> questionIds = results.stream().map(QuestionResult::getId).toList();
		Map<String, Boolean> isCorrectMap = results.stream().collect(toMap(QuestionResult::getId, QuestionResult::isCorrect));
		return repository.findAllByIds(questionIds).stream()
				.map(mapper::mapToDTO)
				.peek(q -> q.setCorrect(isCorrectMap.get(q.getId())))
				.toList();
	}
}

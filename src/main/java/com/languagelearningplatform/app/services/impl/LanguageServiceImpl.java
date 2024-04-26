package com.languagelearningplatform.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.languagelearningplatform.app.dto.LanguageDTO;
import com.languagelearningplatform.app.repository.LanguageRepository;
import com.languagelearningplatform.app.services.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public List<LanguageDTO> getSupportedLanguages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLanguage(LanguageDTO languageDTO) {
		// TODO Auto-generated method stub

	}
}

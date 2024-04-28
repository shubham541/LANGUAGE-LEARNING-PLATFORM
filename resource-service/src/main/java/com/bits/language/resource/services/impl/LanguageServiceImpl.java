package com.bits.language.resource.services.impl;

import java.util.List;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.repository.LanguageRepository;
import com.bits.language.resource.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

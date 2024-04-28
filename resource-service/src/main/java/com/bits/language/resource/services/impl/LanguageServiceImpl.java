package com.bits.language.resource.services.impl;

import java.util.List;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.repository.LanguageRepository;
import com.bits.language.resource.services.LanguageService;
import com.bits.language.resource.services.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

	private final LanguageRepository languageRepository;

	private final ServiceMapper serviceMapper;

	@Override
	public List<LanguageDTO> getSupportedLanguages() {
		return languageRepository.findAll()
				.stream().map(serviceMapper::mapToDTO)
				.toList();
	}

	@Override
	public void addLanguage(LanguageDTO languageDTO) {
		// TODO Auto-generated method stub

	}
}

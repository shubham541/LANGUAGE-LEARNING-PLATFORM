package com.bits.language.resource.services.impl;

import com.bits.language.commons.constant.AppConstants;
import com.bits.language.commons.logger.LogExecutionTime;
import com.bits.language.resource.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Primary
public class CustomJWTServiceImpl implements UserDetailsService {

	private final UserRepo authUserRepo;

	private final Logger log = LoggerFactory.getLogger(CustomJWTServiceImpl.class);

	public CustomJWTServiceImpl(UserRepo authUserRepo) {
		this.authUserRepo = authUserRepo;
	}

	@Override
	@LogExecutionTime
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final var authUser = authUserRepo.findByUsername(username);
		if(log.isDebugEnabled()) {
			log.debug("loadUserByUsername {}", authUser);
		}
		if (authUser == null) {
			throw new UsernameNotFoundException(AppConstants.NO_SUCH_USER_EXISTS);
		}
		return new User(username, authUser.getPassword(), new ArrayList<>());
	}

}

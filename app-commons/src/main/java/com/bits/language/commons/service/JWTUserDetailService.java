package com.bits.language.commons.service;

import java.util.ArrayList;
import java.util.Optional;

import com.bits.language.commons.logger.LogExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bits.language.commons.constant.AppConstants;
import com.bits.language.commons.feign.AuthUserFeign;
import com.bits.language.commons.model.AuthUserDto;

@Service
public class JWTUserDetailService implements UserDetailsService {

	@Autowired
	private AuthUserFeign authUserDelegate;

	@Override
	@LogExecutionTime
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUserDto user = Optional.ofNullable(authUserDelegate.findByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException(AppConstants.NO_SUCH_USER_EXISTS));
		return new User(username, user.getPassword(), new ArrayList<>());
	}

}

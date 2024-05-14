package com.bits.language.commons.config;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bits.language.commons.property.AuthProperties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder bcryptEncoder;

	private final JwtRequestFilter jwtRequestFilter;

	private final AuthProperties authProperties;

	private final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

//	private static final String[] swaggerAntMatchers = new String[] { "/swagger-ui.html/**", "/configuration/**",
//			"/swagger-resources/**", "/v2/api-docs", "/webjars/**" };

	private static final String[] swaggerAntMatchers = new String[] { "/v3/api-docs/**", "/swagger-ui/**",
			"/swagger-ui.html", "/webjars/swagger-ui/**", "/actuator/**" };

	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			UserDetailsService userDetailsService, PasswordEncoder bcryptEncoder, JwtRequestFilter jwtRequestFilter,
			AuthProperties authProperties) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.userDetailsService = userDetailsService;
		this.bcryptEncoder = bcryptEncoder;
		this.jwtRequestFilter = jwtRequestFilter;
		this.authProperties = authProperties;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		final String skipPath = authProperties.getSkipPath();
		final var requests = http.csrf().disable().cors().and().authorizeRequests();
		String[] paths = swaggerAntMatchers;
		if (StringUtils.isNotEmpty(skipPath)) {
			String[] propPaths = skipPath.split(",");
			paths = ArrayUtils.addAll(propPaths, paths);
		}
		if (log.isInfoEnabled()) {
			log.info("configure: skipping the paths -> {}", java.util.Arrays.toString(paths));
		}
		// Give complete access to following paths
		requests.antMatchers(paths).permitAll();
		// Rest should be authenticated
		requests.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response must
		// not be the
		// wildcard '*' when the request's credentials mode is 'include'.
		configuration.setAllowCredentials(false);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}

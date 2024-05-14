package com.bits.language.auth.controller;

import javax.validation.Valid;

import com.bits.language.auth.exception.IncorrectSecurityAnswerRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bits.language.auth.exception.EmailAlreadyExistsRuntimeException;
import com.bits.language.auth.exception.IncorrectPasswordRuntimeException;
import com.bits.language.auth.exception.UsernameTakenRuntimeException;
import com.bits.language.auth.model.EmailLoginRequest;
import com.bits.language.auth.model.ForgotPasswordRequest;
import com.bits.language.auth.model.UsernameLoginRequest;
import com.bits.language.auth.service.AuthService;
import com.bits.language.auth.service.impl.JWTValidationService;
import com.bits.language.commons.exception.NoSuchUserRuntimeException;
import com.bits.language.commons.logger.LogExecutionTime;
import com.bits.language.commons.model.Payload;
import com.bits.language.commons.model.RegisterRequest;
import com.bits.language.commons.model.TokenResponse;
import com.bits.language.commons.model.TokenValidationRequest;
import com.bits.language.commons.model.UsernameResponse;
import com.bits.language.commons.utility.AppUtility;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthService authService;

    private final JWTValidationService jwtValidationService;

    private final AppUtility utility;

    protected AuthController(AuthService authService, AppUtility utility,
                             JWTValidationService jwtValidationService) {
        this.authService = authService;
        this.jwtValidationService = jwtValidationService;
        this.utility = utility;
    }

    @PostMapping(value = "/register")
    @LogExecutionTime
    public ResponseEntity<Payload<TokenResponse>> registerUser(@RequestBody @Valid RegisterRequest request)
            throws UsernameTakenRuntimeException, EmailAlreadyExistsRuntimeException {
        return utility.buildSuccessEntity(HttpStatus.CREATED, authService.registerUser(request));
    }

    @PostMapping(value = "/login/username")
    @LogExecutionTime
    public ResponseEntity<Payload<TokenResponse>> loginWithUsername(@RequestBody @Valid UsernameLoginRequest request)
            throws IncorrectPasswordRuntimeException, NoSuchUserRuntimeException {
        return utility.buildSuccessEntity(authService.loginWithUsername(request));
    }

    @PostMapping(value = "/login/email")
    @LogExecutionTime
    public ResponseEntity<Payload<TokenResponse>> loginWithEmail(@RequestBody @Valid EmailLoginRequest request)
            throws IncorrectPasswordRuntimeException, NoSuchUserRuntimeException {
        return utility.buildSuccessEntity(authService.loginWithEmail(request));
    }

    @GetMapping(value = "/{username}/securityQn")
    @LogExecutionTime
    public ResponseEntity<Payload<String>> fetchSecurityQn(@PathVariable("username") String username)
            throws NoSuchUserRuntimeException {
        return utility.buildSuccessEntity(authService.fetchSecurityQn(username));
    }

    @PostMapping(value = "/{username}/forgot")
    @LogExecutionTime
    public ResponseEntity<Payload<Boolean>> forgotPassword(@PathVariable("username") String username,
                                                           @RequestBody @Valid ForgotPasswordRequest forgotPasswordRequest)
            throws IncorrectSecurityAnswerRuntimeException, NoSuchUserRuntimeException {
        return utility.buildSuccessEntity(authService.forgotPassword(username, forgotPasswordRequest));
    }

    @PostMapping(value = "/validate")
    @LogExecutionTime
    public UsernameResponse validateToken(@Valid @RequestBody TokenValidationRequest request) {
        return jwtValidationService.validateToken(request.getToken());
    }

}

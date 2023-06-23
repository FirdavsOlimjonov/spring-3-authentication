package com.alibou.security.service;

import com.alibou.security.jwt.JwtService;
import com.alibou.security.entity.User;
import com.alibou.security.exceptions.RestException;
import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.SignDTO;
import com.alibou.security.payload.TokenDTO;
import com.alibou.security.repository.UserRepository;
import com.alibou.security.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthServiceImpl(JwtService jwtService, @Lazy AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignDTO signDTO) {
        User user;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signDTO.getUsername(),
                            signDTO.getPassword()
                    ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            user = (User) authentication.getPrincipal();

        } catch (DisabledException | LockedException | CredentialsExpiredException disabledException) {
            throw RestException.restThrow(MessageConstants.USER_NOT_FOUND_OR_DISABLED, HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException | UsernameNotFoundException badCredentialsException) {
            throw RestException.restThrow(MessageConstants.LOGIN_OR_PASSWORD_ERROR, HttpStatus.UNAUTHORIZED);
        }

        return ApiResult.successResponse(jwtService.generateTokenDTO(user), MessageConstants.SUCCESSFULLY_TOKEN_GENERATED);

    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        try {
            String username = jwtService.extractUsername(refreshToken);
            User user = userRepository.findFirstByEmail(username).orElseThrow(
                    () -> RestException.restThrow(MessageConstants.TOKEN_INVALID, HttpStatus.UNAUTHORIZED));

            if (!user.isEnabled()
                    || !user.isAccountNonExpired()
                    || !user.isAccountNonLocked()
                    || !user.isCredentialsNonExpired())
                throw RestException.restThrow(MessageConstants.USER_PERMISSION_RESTRICTION, HttpStatus.UNAUTHORIZED);

            return ApiResult.successResponse(jwtService.generateTokenDTO(user));
        } catch (Exception e) {
            throw RestException.restThrow(MessageConstants.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByEmail(username).orElseThrow(
                () -> RestException.restThrow(MessageConstants.FULL_AUTHENTICATION_REQUIRED, HttpStatus.FORBIDDEN));
    }
}

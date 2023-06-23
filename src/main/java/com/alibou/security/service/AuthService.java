package com.alibou.security.service;

import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.SignDTO;
import com.alibou.security.payload.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> signIn(SignDTO signDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken);

}

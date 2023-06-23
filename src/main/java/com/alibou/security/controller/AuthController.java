package com.alibou.security.controller;

import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.SignDTO;
import com.alibou.security.payload.TokenDTO;
import com.alibou.security.util.RestConstants;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = AuthController.AUTH_CONTROLLER_BASE_PATH)
public interface AuthController {
    String AUTH_CONTROLLER_BASE_PATH = RestConstants.BASE_PATH + "auth";
    String SIGN_IN_PATH = "/sign-in";
    String REFRESH_TOKEN_PATH = "/refresh-token";
    String RESET_PASSWORD_PATH = "/reset-password";
    String GET_LANGUAGES = "/languages";
    String FORGOT_PASSWORD_PATH = "/forgot-password";
    String GET_VALUES_BY_LANGUAGE_PATH = "/language/{language}";

    @PostMapping(path = SIGN_IN_PATH)
    ApiResult<TokenDTO> signIn(@Valid @RequestBody SignDTO signDTO);

    @GetMapping(path = REFRESH_TOKEN_PATH)
    ApiResult<TokenDTO> refreshToken(@RequestHeader(value = "Authorization") String accessToken,
                                     @RequestHeader(value = "RefreshToken") String refreshToken);

//    @GetMapping(path = GET_LANGUAGES)
//    ApiResult<?> getLanguages();

//    @GetMapping(path = GET_VALUES_BY_LANGUAGE_PATH)
//    Map<Object, Object> getValuesByLang(@PathVariable String language);

//    @PostMapping(path = FORGOT_PASSWORD_PATH)
//    ApiResult<EmployeeDTO> forgotPassword(@RequestBody @Valid ForgetPassDTO forgetPassDTO, HttpServletRequest request);

//    @PostMapping(path = RESET_PASSWORD_PATH)
//    ApiResult<EmployeeDTO> resetPassword(@RequestBody @Valid ResetPassDTO resetPassDTO);

}

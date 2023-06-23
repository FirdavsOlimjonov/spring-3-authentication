package com.alibou.security.payload;

import com.alibou.security.util.MessageConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SignDTO {

    @NotBlank(message = MessageConstants.MUST_NOT_BE_BLANK_EMAIL)
    private String username;

    @NotBlank(message = MessageConstants.MUST_NOT_BE_BLANK_PASSWORD)
    private String password;
}

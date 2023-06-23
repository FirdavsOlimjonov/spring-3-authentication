package com.alibou.security.util;

import com.alibou.security.controller.AuthController;

public interface RestConstants {

    String[] OPEN_PAGES = {
            "/*",
            AuthController.AUTH_CONTROLLER_BASE_PATH + "/**",
    };

    String BASE_PATH = "/api/v1/";

}

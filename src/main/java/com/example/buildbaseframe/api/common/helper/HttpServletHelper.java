package com.example.buildbaseframe.api.common.helper;

import javax.servlet.http.HttpServletResponse;

/**
 * <b>HttpServlet帮助类</b>
 *
 * @author lq
 * @version 1.0
 */
public class HttpServletHelper {

    private static final String TOKEN_KEY_IN_RESPONSE = "Authorization";

    /**
     * 更新登录 token
     */
    public static void updateAuthToken(HttpServletResponse response, String token) {
        if (response.containsHeader(TOKEN_KEY_IN_RESPONSE)) {
            response.setHeader(TOKEN_KEY_IN_RESPONSE, token);
        } else {
            response.addHeader(TOKEN_KEY_IN_RESPONSE, token);
        }
    }

}

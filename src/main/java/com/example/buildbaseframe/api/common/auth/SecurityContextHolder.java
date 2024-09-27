package com.example.buildbaseframe.api.common.auth;

/**
 * <b>请求信息上下文</b>
 * <p>
 *     存储身份认证信息等
 * </p>
 *
 * @author lq
 * @version 1.0
 */
public class SecurityContextHolder {

    private static final ThreadLocal<Authentication> HOLDER = new ThreadLocal<>();

    public static void set(Authentication authentication) {
        HOLDER.set(authentication);
    }

    public static Long getUserId() {
        return HOLDER.get().getId();
    }

    public static void remove() {
        HOLDER.remove();
    }

}

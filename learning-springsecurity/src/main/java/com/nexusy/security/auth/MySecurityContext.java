package com.nexusy.security.auth;

import com.nexusy.security.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全认证上下文
 *
 * @author lanhuidong
 * @since 2016-04-24
 */
public class MySecurityContext implements SecurityContext {

    private static final long serialVersionUID = 5513345644841567099L;

    /**
     * 获取当前登录成功的用户
     */
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) (authentication == null ? null : authentication.getPrincipal());
    }

    @Override
    public Authentication getAuthentication() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        throw new UnsupportedOperationException();
    }

}

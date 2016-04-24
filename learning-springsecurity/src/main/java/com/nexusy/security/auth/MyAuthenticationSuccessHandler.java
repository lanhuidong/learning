package com.nexusy.security.auth;

import com.nexusy.security.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final Role userRole = new Role("ROLE_USER");
    private final Role adminRole = new Role("ROLE_ADMIN");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        @SuppressWarnings("unchecked")
        Collection<Role> roles = (Collection<Role>) authentication.getAuthorities();
        if (roles.contains(adminRole)) {
            response.sendRedirect("/admin/index.shtml");
        } else if (roles.contains(userRole)) {
            response.sendRedirect("/user/index.shtml");
        } else {
            response.sendRedirect("/index.shtml");
        }
    }

}
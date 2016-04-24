package com.nexusy.security.auth;

import com.nexusy.security.model.Role;
import com.nexusy.security.model.User;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(MyAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthenticationToken token = (MyAuthenticationToken) authentication;
        User user = (User) token.getPrincipal();
        user = (User) getUserDetailsService().loadUserByUsername(user.getUsername());
        Set<Role> roles = new HashSet<>();
        if ("user".equals(user.getUsername())) {
            Role role = new Role("ROLE_USER");
            roles.add(role);
        } else if ("admin".equals(user.getUsername())) {
            Role role = new Role("ROLE_ADMIN");
            roles.add(role);
        }
        return new MyAuthenticationToken(roles, token.getCredentials(), user);
    }

}

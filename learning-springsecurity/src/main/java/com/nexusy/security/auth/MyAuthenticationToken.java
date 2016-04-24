package com.nexusy.security.auth;

import com.nexusy.security.model.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class MyAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -2928162436443870164L;
    private Object credentials;
    private Object principal;

    public MyAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object credentials, Object principal) {
        super(authorities);
        this.credentials = credentials;
        this.principal = principal;
        this.setAuthenticated(true);
    }

    public MyAuthenticationToken(Object credentials, Object principal) {
        super(null);
        this.credentials = credentials;
        this.principal = principal;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getName() {
        return ((User) principal).getUsername();
    }
}

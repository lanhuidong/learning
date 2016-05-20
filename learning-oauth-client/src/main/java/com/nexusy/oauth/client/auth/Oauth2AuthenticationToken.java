package com.nexusy.oauth.client.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author lan
 * @since 2016-05-19
 */
public class Oauth2AuthenticationToken extends AbstractAuthenticationToken {

    private Object credentials;
    private Object principal;
    private String ip;

    public Oauth2AuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object credentials, Object principal) {
        super(authorities);
        this.credentials = credentials;
        this.principal = principal;
        this.setAuthenticated(true);
    }

    public Oauth2AuthenticationToken(String ip, Object credentials, Object principal) {
        super(null);
        this.credentials = credentials;
        this.principal = principal;
        this.setAuthenticated(false);
        this.ip = ip;
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

    public String getIp() {
        return ip;
    }
}

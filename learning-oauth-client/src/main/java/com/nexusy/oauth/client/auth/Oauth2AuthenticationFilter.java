package com.nexusy.oauth.client.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lan
 * @since 2016-05-19
 */
public class Oauth2AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String authenticationSuccessUrl;
    private String authenticationFailureUrl;

    public void setAuthenticationSuccessUrl(String authenticationSuccessUrl) {
        this.authenticationSuccessUrl = authenticationSuccessUrl;
    }

    public void setAuthenticationFailureUrl(String authenticationFailureUrl) {
        this.authenticationFailureUrl = authenticationFailureUrl;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(authenticationSuccessUrl));
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(authenticationFailureUrl));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Oauth2AuthenticationToken mat = new Oauth2AuthenticationToken("", null, null);
        return getAuthenticationManager().authenticate(mat);
    }
}

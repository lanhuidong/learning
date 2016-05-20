package com.nexusy.oauth.client.auth;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * @author lan
 * @since 2014-09-19
 */
public class Oauth2AuthenticationProvider extends DaoAuthenticationProvider {

    private OAuth2RestTemplate restTemplate;

    public void setRestTemplate(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(Oauth2AuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken accessToken = restTemplate.getAccessToken();
        User user = restTemplate.getForObject("http://localhost:8080/server/me", User.class);
        return new Oauth2AuthenticationToken(user.getAuthorities(), null, user);
    }
}

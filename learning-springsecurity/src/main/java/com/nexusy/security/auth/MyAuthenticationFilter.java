package com.nexusy.security.auth;

import com.nexusy.security.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String authenticationSuccessUrl;
    private String authenticationFailureUrl;

    public static final String SPRING_SECURITY_LAST_TOPSESSION_KEY = "SPRING_SECURITY_LAST_TOPSESSION_KEY";

    public String getAuthenticationSuccessUrl() {
        return authenticationSuccessUrl;
    }

    public void setAuthenticationSuccessUrl(String authenticationSuccessUrl) {
        this.authenticationSuccessUrl = authenticationSuccessUrl;
    }

    public String getAuthenticationFailureUrl() {
        return authenticationFailureUrl;
    }

    public void setAuthenticationFailureUrl(String authenticationFailureUrl) {
        this.authenticationFailureUrl = authenticationFailureUrl;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(getAuthenticationFailureUrl()));
        setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        MyAuthenticationToken mat = new MyAuthenticationToken(password, user);
        return getAuthenticationManager().authenticate(mat);
    }

}

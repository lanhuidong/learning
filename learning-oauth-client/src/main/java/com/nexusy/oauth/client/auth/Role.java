package com.nexusy.oauth.client.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lan
 * @since 2016-05-19
 */
public class Role implements GrantedAuthority {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}

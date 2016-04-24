package com.nexusy.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lanhuidong
 * @since 2016-04-24
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

package com.nexusy.security.model;

import java.util.Collection;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class UrlResource {

    private String url;
    private Collection<Role> roles;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}

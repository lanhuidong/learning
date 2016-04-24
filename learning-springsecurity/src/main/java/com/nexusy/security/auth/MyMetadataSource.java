package com.nexusy.security.auth;

import com.nexusy.security.model.Role;
import com.nexusy.security.model.UrlResource;
import com.nexusy.security.service.UrlResourceService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class MyMetadataSource implements FilterInvocationSecurityMetadataSource {

    private PathMatcher pathMatcher = new AntPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> urlResourceMap = null;

    private UrlResourceService urlResourceService;

    public void setUrlResourceService(UrlResourceService urlResourceService) {
        this.urlResourceService = urlResourceService;
    }

    public void init() {
        urlResourceMap = new HashMap<>();
        List<UrlResource> urlResources = urlResourceService.loadAll();
        for (UrlResource urlResource : urlResources) {
            Collection<ConfigAttribute> atts = new ArrayList<>();
            Collection<Role> roles = urlResource.getRoles();
            for (Role role : roles) {
                ConfigAttribute ca = new SecurityConfig(role.getName());
                atts.add(ca);
            }
            if (urlResourceMap.containsKey(urlResource.getUrl())) {
                urlResourceMap.get(urlResource.getUrl()).addAll(atts);
            } else {
                urlResourceMap.put(urlResource.getUrl(), atts);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (MySecurityContext.getCurrentUser() == null) {
            HttpServletResponse response = ((FilterInvocation) object).getResponse();
            try {
                response.sendRedirect("/login.html");
            } catch (IOException e) {
            }
            throw new AccessDeniedException("Access Denied");
        }

        String requestURL = ((FilterInvocation) object).getRequest().getServletPath();

        Iterator<String> it = urlResourceMap.keySet().iterator();
        while (it.hasNext()) {
            String url = it.next();
            if (pathMatcher.match(requestURL, url)) {
                return urlResourceMap.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}

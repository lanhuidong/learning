package com.nexusy.security.service;

import com.nexusy.security.model.Role;
import com.nexusy.security.model.UrlResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class UrlResourceService {

    public List<UrlResource> loadAll() {
        List<UrlResource> resources = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                UrlResourceService.class.getClassLoader().getResourceAsStream("urlresources.properties")))) {
            String line;
            while ((line = br.readLine()) != null) {
                UrlResource resource = new UrlResource();
                String[] elem = line.split("=");
                resource.setUrl(elem[0]);
                List<Role> roles = new ArrayList<>();
                Role role = new Role(elem[1]);
                roles.add(role);
                resource.setRoles(roles);
                resources.add(resource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }
}

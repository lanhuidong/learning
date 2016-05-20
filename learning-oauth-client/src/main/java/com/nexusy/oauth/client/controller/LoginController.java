package com.nexusy.oauth.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lan
 * @since 2016-05-19
 */
@Controller
public class LoginController {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping("/login.shtml")
    public void login() {
        OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
        System.out.println(accessToken);
    }
}

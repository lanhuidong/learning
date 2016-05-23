package com.nexusy.oauth.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lan
 * @since 2016-05-19
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout.shtml")
    public ModelAndView logout() {
        return new ModelAndView("/index");
    }
}

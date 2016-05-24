package com.nexusy.oauth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lan
 * @since 2016-05-24
 */
@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2Controller {

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("/u/approval");
        if (request.getAttribute("_csrf") != null) {
            mav.addObject("_csrf", request.getAttribute("_csrf"));
        }
        return mav;
    }
}

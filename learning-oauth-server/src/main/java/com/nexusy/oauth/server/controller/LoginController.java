package com.nexusy.oauth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lan
 * @since 2016-05-19
 */
@Controller
public class LoginController {

    @RequestMapping(value = {"/","/index"})
    public ModelAndView index(){
        return new ModelAndView("/index");
    }
}

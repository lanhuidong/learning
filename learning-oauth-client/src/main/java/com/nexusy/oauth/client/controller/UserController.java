package com.nexusy.oauth.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lan
 * @since 2016-05-19
 */
@Controller
public class UserController {

    @RequestMapping("/u/index")
    public ModelAndView index(){
        return new ModelAndView("/u/index");
    }
}

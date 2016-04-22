package com.nexusy.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lan
 * @since 2016-04-21
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index.shtml"})
    public ModelAndView index() {
        System.out.println("index");
        return new ModelAndView("/index");
    }
}

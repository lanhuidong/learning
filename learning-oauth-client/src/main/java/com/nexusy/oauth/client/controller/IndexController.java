package com.nexusy.oauth.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lan
 * @since 2016-05-18
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "index.shtml"})
    public ModelAndView index() {
        return new ModelAndView("/index");
    }
}

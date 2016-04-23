package com.nexusy.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lanhuidong
 * @since 2016-04-22
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index.shtml")
    public ModelAndView admin(){
        return new ModelAndView("/admin/index");
    }
}

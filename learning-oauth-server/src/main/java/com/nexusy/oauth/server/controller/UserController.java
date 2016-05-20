package com.nexusy.oauth.server.controller;

import com.nexusy.oauth.server.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * @author lan
 * @since 2016-05-19
 */
@Controller
public class UserController {

    @RequestMapping("/u/index")
    public ModelAndView index() {
        return new ModelAndView("/u/index");
    }

    @RequestMapping("/me")
    @ResponseBody
    public User showUser(Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        return user;
    }
}

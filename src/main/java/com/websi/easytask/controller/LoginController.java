package com.websi.easytask.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gabriel on 01/06/17.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login (@AuthenticationPrincipal User user) {
        if (user != null)
            return "redirect:/";
        return "login";
    }
}

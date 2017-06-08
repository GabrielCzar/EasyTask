package com.websi.gethackers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gabriel on 05/06/17.
 */
@Controller
public class ControllerHome {

    @RequestMapping("/")
    public String home() {
        return "/index";
    }

}

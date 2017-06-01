package com.websi.gethackers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gabriel on 23/05/17.
 */

@Controller
public class ErrorPagesController {

    @RequestMapping("/404")
    public String notFound () {
        return "/error/404";
    }

    @RequestMapping("/403")
    public String forbidden () {
        return "/error/403";
    }

    @RequestMapping("/500")
    public String internalServerError () {
        return "/error/500";
    }

}

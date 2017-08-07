package com.easytask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.easytask.util.Constants.FORBIDDEN;
import static com.easytask.util.Constants.INTERNAL_SERVER_ERROR;
import static com.easytask.util.Constants.NOT_FOUND;

@Controller
public class ErrorPagesController {

    @RequestMapping("/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound () {
        return NOT_FOUND;
    }

    @RequestMapping("/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden () {
        return FORBIDDEN;
    }

    @RequestMapping("/500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError () {
        return INTERNAL_SERVER_ERROR;
    }

}

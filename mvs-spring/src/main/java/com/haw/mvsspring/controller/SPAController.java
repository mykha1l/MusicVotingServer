package com.haw.mvsspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SPAController {

    @RequestMapping(value = "/{symbolicName:[A-z-]+}")
    public String upload() {
        return "/index.html";
    }

}

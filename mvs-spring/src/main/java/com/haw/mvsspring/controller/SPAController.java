package com.haw.mvsspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SPAController {

    @RequestMapping(value = "/{symbolicName:[A-z]+}-admin")
    public String admin() {
        return "/admin.html";
    }

    @RequestMapping(value = "/{symbolicName:[A-z]+}")
    public String user() {
        return "/index.html";
    }


}

package com.capstone.feedme.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {

    @RequestMapping("/about")
    public String index(){
        return "about/index.html";
    }
}

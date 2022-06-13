package com.capstone.feedme.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KeysController {

    @Value("${SPOON_API_KEY_01")
    private String SPOON_API_KEY_01;

    @Value("${SPOON_API_KEY_02")
    private String SPOON_API_KEY_02;

    @Value("${SPOON_API_KEY_03")
    private String SPOON_API_KEY_03;

    @Value("${FS_API_KEY")
    private String FS_API_KEY;

    @GetMapping(path = "/keys", produces = "application/javascript")
    @ResponseBody
    public String keys() {
        String output = "";
        output += "const SPOON_API_KEY_01 = " + SPOON_API_KEY_01 + "; ";
        output += "const SPOON_API_KEY_02 = " + SPOON_API_KEY_02 + "; ";
        output += "const SPOON_API_KEY_03 = " + SPOON_API_KEY_03 + "; ";
        output += "const FS_API_KEY = " + FS_API_KEY + "; ";
        return output;

    }



}

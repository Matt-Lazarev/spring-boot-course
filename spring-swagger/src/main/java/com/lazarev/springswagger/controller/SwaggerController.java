package com.lazarev.springswagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    @GetMapping
    public String swaggerHome(){
        return "redirect:swagger-ui.html";
    }
}

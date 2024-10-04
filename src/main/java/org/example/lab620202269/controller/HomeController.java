package org.example.lab620202269.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/inicio")
    public String index(){
        return "index";
    }
}


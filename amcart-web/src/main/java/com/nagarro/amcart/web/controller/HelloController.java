package com.nagarro.amcart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/hello")
    public String hello() {
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String sayHello(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}

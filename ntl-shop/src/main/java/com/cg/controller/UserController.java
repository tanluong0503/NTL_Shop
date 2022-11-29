package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/home")
    public ModelAndView showListAccount(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sellProducts/product_list");
        return modelAndView;
    }
}

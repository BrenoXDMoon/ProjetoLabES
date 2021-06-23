package br.com.fatec.chopperhouse.control.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping
    public String index(ModelAndView modelAndView){

        return "index";
    }
}

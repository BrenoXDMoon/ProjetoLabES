package br.com.fatec.chopperhouse.control.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("nome", "Brenao");
        return "hello";
    }
}

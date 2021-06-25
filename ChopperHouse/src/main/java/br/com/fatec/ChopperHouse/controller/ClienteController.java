package br.com.fatec.ChopperHouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @GetMapping("form")
    public String formulario(){
        return "cliente/form";
    }
}

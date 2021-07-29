package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    IClienteService clienteService;

    @GetMapping
    public ModelAndView index() {

        ModelAndView mv = null;

        if(clienteService.atualUsuarioLogado() == null){
            mv = new ModelAndView("/index");
            return mv;
        }else{
            mv = new ModelAndView("/index");
            mv.addObject("cliente", clienteService.atualUsuarioLogado());
            return mv;
        }
    }
}

package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    IClienteService clienteService;

    @GetMapping
    public ModelAndView dashboard(){
        ModelAndView mv = new ModelAndView("admin/dashboard");

        return mv;
    }

    @GetMapping("clientes")
    public ModelAndView listaClientes(){
        ModelAndView mv = new ModelAndView("admin/cliente/lista");

        mv.addObject("admin", clienteService.atualUsuarioLogado());
        mv.addObject("clientes", clienteService.buscarTodos());

        return mv;
    }

}

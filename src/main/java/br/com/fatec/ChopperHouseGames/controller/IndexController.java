package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Jogo;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import br.com.fatec.ChopperHouseGames.service.IJogoService;
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

    @Autowired
    IJogoService jogoService;

    @GetMapping
    public ModelAndView index() {

        ModelAndView mv = new ModelAndView("/index");
        List<Jogo> jogos = jogoService.listarAtivos();
        mv.addObject("jogos", jogos);

        if(clienteService.atualUsuarioLogado() == null){
        }else{
            mv.addObject("cliente", clienteService.atualUsuarioLogado());
        }

        return mv;
    }
}

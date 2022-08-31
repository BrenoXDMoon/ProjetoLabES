package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import br.com.fatec.chopperhousegames.core.domain.service.ClienteService;
import br.com.fatec.chopperhousegames.core.domain.service.JogoService;
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
    ClienteService clienteService;

    @Autowired
    JogoService jogoService;

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

package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.inbound.facade.ClienteFacade;
import br.com.fatec.chopperhousegames.inbound.facade.JogoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.JogoDTO;
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
    ClienteFacade clienteFacade;

    @Autowired
    JogoFacade jogoFacade;

    @GetMapping
    public ModelAndView index() {

        ModelAndView mv = new ModelAndView("/index");
        List<JogoDTO> jogos = jogoFacade.listarJogosAtivos();
        mv.addObject("jogos", jogos);
        if(clienteFacade.atualUsuarioLogado() != null){
            mv.addObject("cliente", clienteFacade.atualUsuarioLogado());
        }
        return mv;
    }
}

package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.core.domain.service.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/cupons")
public class CupomController {

    private final CupomService cupomService;

    @Autowired
    public CupomController(CupomService cupomService) {
        this.cupomService = cupomService;
    }

    @GetMapping
    public ModelAndView listaCuponsAdmin(){
        ModelAndView mv = new ModelAndView("admin/cupom/lista");

        mv.addObject("cupons", cupomService.listarTodosCupons());

        return mv;
    }


}

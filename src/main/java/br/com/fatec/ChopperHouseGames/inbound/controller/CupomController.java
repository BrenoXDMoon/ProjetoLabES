package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.service.ICupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/cupons")
public class CupomController {

    @Autowired
    ICupomService cupomService;

    @GetMapping
    public ModelAndView listaCuponsAdmin(){
        ModelAndView mv = new ModelAndView("admin/cupom/lista");

        mv.addObject("cupons", cupomService.listarTodos());

        return mv;
    }


}

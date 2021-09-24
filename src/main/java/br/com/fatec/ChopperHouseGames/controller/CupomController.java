package br.com.fatec.ChopperHouseGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/cupons")
public class CupomController {

    @GetMapping
    public ModelAndView listaCuponsAdmin(){
        ModelAndView mv = new ModelAndView("admin/cupom/lista");

        return mv;
    }


}

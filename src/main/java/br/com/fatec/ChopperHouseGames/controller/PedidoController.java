package br.com.fatec.ChopperHouseGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PedidoController {

    @GetMapping("pedido/visualizar/{id}")
    public ModelAndView visualizarPedido(){
        ModelAndView mv = new ModelAndView("");

        return mv;
    }
}

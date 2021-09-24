package br.com.fatec.ChopperHouseGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/pedidos")
public class PedidoController {

    @GetMapping
    public ModelAndView listaPedidosAdmin(){
       ModelAndView mv = new ModelAndView("admin/pedido/listaPedido");

       return mv;
    }

    @GetMapping("visualizar")
    public ModelAndView visualizarPedidoAdmin(){
        ModelAndView mv = new ModelAndView("admin/pedido/detalhe");

        return mv;
    }
}

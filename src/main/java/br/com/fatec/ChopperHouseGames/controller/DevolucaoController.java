package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Devolucao;
import br.com.fatec.ChopperHouseGames.domain.StatusDevolucao;
import br.com.fatec.ChopperHouseGames.service.IDevolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/devolucoes")
public class DevolucaoController {


    @Autowired
    IDevolucaoService devolucaoService;

    @GetMapping
    public ModelAndView listarDevolucoes(ModelAndView mv){

        if(mv == null){
            mv = new ModelAndView();
        }
        mv.setViewName("admin/pedido/listaDevolucao");
        mv.addObject("pedidos", devolucaoService.buscarTodos());

        return mv;
    }

    @PostMapping("{id}")
    public ModelAndView alterarStatusDevolucao(@PathVariable("id") Devolucao devolucao){
        ModelAndView mv = new ModelAndView("redirect:/admin/devolucoes");
        devolucaoService.editar(devolucao);
        System.out.println("tentando editar de " + devolucao.getStatusDevolucao().getDescricao());
        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView visualizarDevolucao(@PathVariable("id") Devolucao devolucao){

        ModelAndView mv = new ModelAndView("admin/pedido/detalheDevolucao");

        mv.addObject("devolucao", devolucaoService.buscarById(devolucao.getId()));

        return mv;
    }

    @PostMapping("{id}/aceitar")
    public ModelAndView aceitarTroca(@PathVariable("id") Devolucao devolucao){

        ModelAndView mv = new ModelAndView("redirect:/admin/devolucoes");
        devolucaoService.aceitar(devolucao);
        return mv;
    }

    @PostMapping("{id}/recusar")
    public ModelAndView recusarTroca(@PathVariable("id") Devolucao devolucao){

        ModelAndView mv = new ModelAndView("redirect:/admin/devolucoes");
        devolucaoService.recusar(devolucao);
        return mv;
    }

    @PostMapping("{id}/processar")
    public ModelAndView processarTroca(@PathVariable("id") Devolucao devolucao){

        ModelAndView mv = new ModelAndView("redirect:/admin/devolucoes");
        devolucaoService.processar(devolucao);
        return mv;
    }

    @PostMapping("{id}/receber")
    public ModelAndView receberTroca(@PathVariable("id") Devolucao devolucao){

        ModelAndView mv = new ModelAndView("redirect:/admin/devolucoes");
        devolucaoService.finalizar(devolucao);
        return mv;
    }
}

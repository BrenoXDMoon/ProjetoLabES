package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.inbound.facade.ClienteFacade;
import br.com.fatec.chopperhousegames.inbound.facade.GraficoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final ClienteFacade facade;
    private final GraficoFacade graficoFacade;

    @Autowired
    public AdminController(ClienteFacade facade, GraficoFacade pedidoService) {
        this.facade = facade;
        this.graficoFacade = pedidoService;
    }

    @GetMapping("/")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("admin/dashboard");

        LocalDate inital = LocalDate.now().minusMonths(3);
        LocalDate finalDate = LocalDate.now().plusMonths(3);

        ChartDTO orders = graficoFacade.buscarTodosCriadosEntre(inital, finalDate, 0);
        List<HashMap<String, Double>> cards = graficoFacade.preencherIndexCards();

        mv.addObject("ordersFiltered", orders);
        mv.addObject("cards", cards);

        return mv;
    }

    @GetMapping("filterdata")
    @ResponseBody
    public ChartDTO getOrdersFiltered(String initialDateParam, String finalDateParam, Integer searchType){
        LocalDate dataInicial;
        LocalDate dataFinal;

        if (null != initialDateParam && initialDateParam.length() > 0) {
            dataInicial = LocalDate.parse(initialDateParam);
        }else {
            dataInicial = LocalDate.now().minusMonths(3);
        }

        if (finalDateParam != null && finalDateParam.length() > 0) {
            dataFinal = LocalDate.parse(finalDateParam);
        } else {
            dataFinal = LocalDate.now();
        }

        return graficoFacade.buscarTodosCriadosEntre(dataInicial, dataFinal, searchType);
    }

    @GetMapping("clientes")
    public ModelAndView listaClientes() {
        ModelAndView mv = new ModelAndView("admin/cliente/lista");

        mv.addObject("clientes", facade.listarTodosOsClientes());

        return mv;
    }

    @PostMapping("clientes")
    public ModelAndView desativaEAtivaCliente(@RequestParam Long id, RedirectAttributes attributes) {

        ModelAndView mv = new ModelAndView("admin/cliente/lista");
        facade.ativaInativaCliente(id);
        mv.addObject("clientes", facade.listarTodosOsClientes());
        attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");

        return mv;
    }
}

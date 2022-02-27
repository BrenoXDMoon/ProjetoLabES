package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.service.IPedidoService;
import br.com.fatec.ChopperHouseGames.inbound.facade.ClienteFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ChartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private ClienteFacade facade;

    //    @Autowired
//    ClienteService clienteService;
//
//    @Autowired
//    ClienteRepository clienteRepository;
//
//    @Autowired
//    EnderecoRepository enderecoRepository;
//
//    @Autowired
//    CartaoCreditoRepository cartaoCreditoRepository;
//
//    Facade facade;
//    @Autowired
//    private IPedidoService pedidoService;
//
//    @GetMapping("/")
//    public ModelAndView dashboard(){
//        ModelAndView mv = new ModelAndView("admin/dashboard");
//
//        Date inital;
//        Date finalDate;
//
//        inital = Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC));
//        finalDate = Date.from(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.UTC));
//
//        ChartDto orders = pedidoService.buscarTodosCriadosEntre(inital, finalDate, 0);
//        List<HashMap<String, Double>> cards = pedidoService.preencherIndexCards();
//
//        mv.addObject("ordersFiltered", orders);
//        mv.addObject("cards", cards);
//
//        return mv;
//    }
//
//    @GetMapping("filterdata")
//    @ResponseBody
//    public ChartDto getOrdersFiltered(String initialDateParam, String finalDateParam, Integer searchType) throws ParseException {
//        Date inital;
//        Date finalDate;
//
//        inital = Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC));
//        finalDate = Date.from(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.UTC));
//
//        if(null != initialDateParam && initialDateParam.length() > 0){
//            inital = new SimpleDateFormat("yyyy-MM-dd").parse(initialDateParam);
//        }
//
//        if(finalDateParam != null && finalDateParam.length() > 0){
//            finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(finalDateParam);
//        }else {
//            Date data = new Date();
//            finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(data.toInstant().toString());
//        }
//
//        return pedidoService.buscarTodosCriadosEntre(inital, finalDate, searchType);
//    }
//
    @GetMapping("clientes")
    public ModelAndView listaClientes(){
        ModelAndView mv = new ModelAndView("admin/cliente/lista");

        mv.addObject("clientes", facade.listar());

        return mv;
    }

    @PostMapping("clientes")
    public ModelAndView desativaEAtivaCliente(@RequestParam Integer id, RedirectAttributes attributes){

        ModelAndView mv = new ModelAndView("admin/cliente/lista");
        facade.ativaInativa(id);
        mv.addObject("clientes", facade.listar());
        attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");

        return mv;
    }
}
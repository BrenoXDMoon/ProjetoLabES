package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.converter.ConverterCliente;
import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.dto.ChartDto;
import br.com.fatec.ChopperHouseGames.facade.impl.Facade;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import br.com.fatec.ChopperHouseGames.service.IPedidoService;
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

    @Autowired
    IClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    Facade facade;
    @Autowired
    private IPedidoService pedidoService;

    @GetMapping("/")
    public ModelAndView dashboard(){
        ModelAndView mv = new ModelAndView("admin/dashboard");

        Date inital;
        Date finalDate;

        inital = Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC));
        finalDate = Date.from(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.UTC));

        ChartDto orders = pedidoService.buscarTodosCriadosEntre(inital, finalDate, 0);
        List<HashMap<String, Double>> cards = pedidoService.preencherIndexCards();

        mv.addObject("ordersFiltered", orders);
        mv.addObject("cards", cards);

        return mv;
    }

    @GetMapping("filterdata")
    @ResponseBody
    public ChartDto getOrdersFiltered(String initialDateParam, String finalDateParam, Integer searchType) throws ParseException {
        Date inital;
        Date finalDate;

        inital = Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC));
        finalDate = Date.from(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.UTC));

        if((null != initialDateParam || null != finalDateParam) && (initialDateParam.length() > 0 && finalDateParam.length() > 0)){
            inital = new SimpleDateFormat("yyyy-MM-dd").parse(initialDateParam);
            finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(finalDateParam);
        }

        return pedidoService.buscarTodosCriadosEntre(inital, finalDate, searchType);
    }

    @GetMapping("clientes")
    public ModelAndView listaClientes(){
        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        ModelAndView mv = new ModelAndView("admin/cliente/lista");

        List<Cliente> clientes = ConverterCliente.converte(facade.listar(new Cliente()).getEntidades());

        mv.addObject("clientes", clientes);
        mv.addObject("admin", clienteService.atualUsuarioLogado());

        return mv;
    }

    @PostMapping("clientes")
    public ModelAndView desativaEAtivaCliente(@RequestParam String id, RedirectAttributes attributes){

        ModelAndView mv = new ModelAndView("admin/cliente/lista");

        Cliente cliente = clienteService.buscarById(Integer.parseInt(id));

        if(cliente.isAtivo()){
            cliente.setAtivo(false);
        }else{
            cliente.setAtivo(true);
        }

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        facade.editar(cliente);

        List<Cliente> clientes = ConverterCliente.converte(facade.listar(new Cliente()).getEntidades());
        mv.addObject("clientes", clientes);
        attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");

        return mv;
    }
}

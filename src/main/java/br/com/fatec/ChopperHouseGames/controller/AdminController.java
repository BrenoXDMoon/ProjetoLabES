package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.converter.ConverterCliente;
import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.domain.Resultado;
import br.com.fatec.ChopperHouseGames.facade.IFacade;
import br.com.fatec.ChopperHouseGames.facade.impl.Facade;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping
    public ModelAndView dashboard(){
        ModelAndView mv = new ModelAndView("admin/dashboard");

        return mv;
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

}

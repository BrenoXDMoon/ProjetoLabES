package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.CartaoCredito;
import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.dto.request.CartaoCreditoDtoForm;
import br.com.fatec.ChopperHouseGames.facade.impl.Facade;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente/perfil")
public class CartaoCreditoController {

    private Facade facade;

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/{id}/cartoes")
    public ModelAndView listarCartoes(@PathVariable("id") Cliente cliente, CartaoCreditoDtoForm dto) {
        ModelAndView mv = new ModelAndView("/cliente/listaCartoes");

        facade.listar(new CartaoCredito());

        mv.addObject("cliente",cliente);
        clienteService.usuarioLogado(cliente.getId(), mv);
        return mv;
    }
}

package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.domain.Pedido;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import br.com.fatec.ChopperHouseGames.service.IPedidoService;
import br.com.fatec.ChopperHouseGames.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cliente/perfil/{id}/pedidos")
public class PedidoClienteController {

    @Autowired
    IStatusService statusService;

    @Autowired
    IClienteService clienteService;

    @Autowired
    IPedidoService pedidoService;

    @PostMapping("visualizar/{idPed}/cancelamento")
    public ModelAndView solicitaTroca(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido){

        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/pedidos");

        pedido.setStatus(statusService.buscarById(13));

        pedidoService.editar(pedido);

        mv.addObject("mensagem", "Solicitação de cancelamento enviada com sucesso!");

        mv.addObject("cliente", clienteService.atualUsuarioLogado());

        return mv;
    }
}

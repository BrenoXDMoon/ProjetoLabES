package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.Pedido;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.service.PedidoService;
import br.com.fatec.ChopperHouseGames.core.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cliente/perfil/{id}/pedidos")
public class PedidoClienteController {

    private final StatusService statusService;

    private final ClienteService clienteService;

    private final PedidoService pedidoService;

    @Autowired
    public PedidoClienteController(StatusService statusService, ClienteService clienteService, PedidoService pedidoService) {
        this.statusService = statusService;
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ModelAndView listarPedidos(@PathVariable("id") Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/pedido/lista");
        //gerar pedidos reais
        mv.addObject("cliente", cliente);

        return mv;
    }

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

package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.core.domain.entity.Cliente;
import br.com.fatec.chopperhousegames.core.domain.entity.Pedido;
import br.com.fatec.chopperhousegames.core.domain.entity.Status;
import br.com.fatec.chopperhousegames.core.domain.service.ClienteService;
import br.com.fatec.chopperhousegames.core.domain.service.PedidoService;
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

    private final ClienteService clienteService;

    private final PedidoService pedidoService;

    @Autowired
    public PedidoClienteController(ClienteService clienteService, PedidoService pedidoService) {
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

        //TODO: REFATORAR PARA USAR O SERVICE
        pedido.setStatus(Status.CANCELAMENTO_SOLICITADO);

        pedidoService.editarPedido(pedido);

        mv.addObject("mensagem", "Solicitação de cancelamento enviada com sucesso!");

        mv.addObject("cliente", clienteService.atualClienteLogado());

        return mv;
    }
}

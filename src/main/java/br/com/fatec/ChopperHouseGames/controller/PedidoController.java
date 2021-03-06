package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Item;
import br.com.fatec.ChopperHouseGames.domain.Pedido;
import br.com.fatec.ChopperHouseGames.repository.JogoRepository;
import br.com.fatec.ChopperHouseGames.service.IPedidoService;
import br.com.fatec.ChopperHouseGames.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IStatusService statusService;
    @Autowired
    private JogoRepository jogoRepository;

    @GetMapping("pedidos")
    public ModelAndView listaPedidosAdmin(ModelAndView mv){

        if(mv == null){
            mv = new ModelAndView();
        }

        mv.setViewName("admin/pedido/listaPedido");

        mv.addObject("pedidos", pedidoService.buscarTodos());

        return mv;
    }

    @GetMapping("pedidos/visualizar/{id}")
    public ModelAndView visualizarPedidoAdmin( @PathVariable("id") Pedido pedido){
        ModelAndView mv = new ModelAndView("admin/pedido/detalhe");

        mv.addObject("pedido", pedidoService.buscarById(pedido.getId()));

        mv.addObject("statusPedidos", statusService.listarTodos());
        return mv;
    }

    @PostMapping("pedidos/visualizar/{id}/editar")
    public ModelAndView alteraPedido(@RequestParam(required = false, name = "statusId") String statusId, @PathVariable Pedido id){

        Pedido pedido = pedidoService.buscarById(id.getId());
        pedido.setStatus(statusService.buscarById(Integer.parseInt(statusId)));
        ModelAndView mv = new ModelAndView();
        pedidoService.editar(pedido);
        listaPedidosAdmin(mv);
        return mv;
    }

    @GetMapping("cancelamentos")
    public ModelAndView listarCancelamentos(ModelAndView mv) {

        if (mv == null) {
            mv = new ModelAndView();
        }

        mv.setViewName("admin/pedido/listaCancelamento");

        mv.addObject("pedidos", pedidoService.buscarTodos());

        return mv;
    }

    @PostMapping("pedidos/visualizar/{id}/aceitarCancelamento")
    public ModelAndView aceitarCancelamento(@PathVariable("id") Pedido pedido){

        ModelAndView mv = new ModelAndView("redirect:/admin/cancelamentos");

        pedido.setStatus(statusService.buscarByNome("CANCELAMENTO ACEITO"));
        for(Item item : pedido.getItens()){//devolvendo produto ao estoque
            item.getJogo().setQuantidadeDisponivel(item.getJogo().getQuantidadeDisponivel() + item.getQuantidade());
            jogoRepository.saveAndFlush(item.getJogo());
        }

        pedido.setStatus(statusService.buscarById(12));

        pedidoService.editar(pedido);

        return mv;
    }

    @PostMapping("pedidos/visualizar/{id}/recusarCancelamento")
    public ModelAndView recusarCancelamento(@PathVariable("id") Pedido pedido){

        ModelAndView mv = new ModelAndView("redirect:/admin/cancelamentos");

        pedido.setStatus(statusService.buscarByNome("CANCELAMENTO RECUSADO"));

        pedidoService.editar(pedido);

        return mv;
    }
}

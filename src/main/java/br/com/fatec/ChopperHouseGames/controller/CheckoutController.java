package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.*;
import br.com.fatec.ChopperHouseGames.service.ICarrinhoService;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/cliente/{id}/checkout")
public class CheckoutController {

    @Autowired
    IClienteService clienteService;

    @Autowired
    ICarrinhoService carrinhoService;

    @GetMapping
    public ModelAndView checkout(@PathVariable("id") Cliente cliente, Pedido pedido) {
        ModelAndView mv = new ModelAndView("pedido/checkout");
        mv.addObject("cliente", cliente);
        if(cliente.getCarrinho() == null){
            Carrinho carrinho = new Carrinho();
            cliente.setCarrinho(carrinho);
            mv.addObject("total", 0);
        }else{
            mv.addObject("total", cliente.getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum());
        }
        mv.addObject("pedido", pedido);
        mv.addObject("cartaoCredito", new CartaoCredito());
        mv.addObject("endereco", new Endereco());
        mv.addObject("tipoEndereco", TIPO_ENDERECO.values());

        clienteService.usuarioLogado(cliente.getId(), mv);
        return mv;
    }

    @GetMapping("finaliza-pedido")
    public ModelAndView finishCheckout(@PathVariable("id") Cliente cliente) {
        ModelAndView mv = new ModelAndView("redirect:/cliente/" + cliente.getId() + "/pedidos");

        return mv;
    }

    @PostMapping("/adicionarJogo")
    public ModelAndView addCheckoutItem(@PathVariable("id") Cliente cliente, Integer id, Integer quantidade) {
        if(!clienteService.usuarioIsLogado(cliente.getId())){
            return new ModelAndView("redirect:/");
        }
        carrinhoService.adicionarItemCarrinho(cliente, id, quantidade);

        ModelAndView mv = new ModelAndView("redirect:/cliente/" + cliente.getId() + "/checkout");
        mv.addObject("cliente", cliente);

        return mv;
    }

}

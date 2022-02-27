package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.*;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDto;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDto;
import br.com.fatec.ChopperHouseGames.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/cliente/perfil/{id}/checkout")
public class CheckoutController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ICarrinhoService carrinhoService;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    CartaoService cartaoService;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private ICupomService cupomService;

    @GetMapping
    public ModelAndView checkout(@PathVariable("id") Cliente cliente, Pedido pedido, ModelAndView mv) {

        double totalPedido = cliente.getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum();

        if(mv == null){
            mv = new ModelAndView();
        }

        pedido = new Pedido();
        mv.setViewName("pedido/checkout");
        mv.addObject("cliente", cliente);
        mv.addObject("totalProdutos", cliente.getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum());
        mv.addObject("totalPedido", totalPedido + 15);
        pedido.setTotal(totalPedido);
        mv.addObject("pedido", new Pedido());
        mv.addObject("cartaoCredito", new CartaoCredito());
        mv.addObject("endereco", new Endereco());
        mv.addObject("tipoEnderecos", TIPO_ENDERECO.values());
        mv.addObject("bandeiras", BANDEIRA.values());
        mv.addObject("cupons", cupomService.listarCupomDesconto());
        mv.addObject("cupomZerado", cupomService.buscarCupomZerado());
        mv.addObject("cuponsTroca", cupomService.listarCupomTroca());

        clienteService.usuarioLogado(cliente.getId(), mv);
        return mv;
    }

    @PostMapping
    public ModelAndView finalizarPedido(@PathVariable("id") Cliente cliente, @Valid Pedido pedido, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/pedidos");

        System.out.println(pedido.getTotal());

        pedido.setCliente(clienteService.atualUsuarioLogado());
        pedidoService.salvar(pedido, result);

        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return checkout(cliente, pedido, mv);
        }

        return mv;
    }

    @PostMapping("/adicionarJogo")
    public ModelAndView adicionaItemCarrinho(@PathVariable("id") Cliente cliente, Integer idJogo, Integer quantidade) {
        if(!clienteService.usuarioIsLogado(cliente.getId())){
            return new ModelAndView("redirect:/");
        }
        carrinhoService.adicionarItemCarrinho(cliente, idJogo, quantidade);

        cliente = clienteService.atualUsuarioLogado();
        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/checkout");
        mv.addObject("cliente", cliente);

        return mv;
    }

    @PostMapping("removeItem")
    public ModelAndView removeItemCarrinho(@PathVariable("id") Cliente cliente, Integer id){
        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/checkout");
        cliente = clienteService.atualUsuarioLogado();
        carrinhoService.removerItemCarrinho(cliente, id);
        mv.addObject("cliente", cliente);
        return mv;
    }

    @GetMapping("/salvarEndereco")
    public ModelAndView paginaCriaEnderecoCheckout(@PathVariable("id") Cliente cliente, EnderecoDto enderecoDto, ModelAndView mv){
        if(mv == null){
            mv = new ModelAndView();
        }
        mv.setViewName("pedido/formEndereco");
        mv.addObject("tiposEnd", TIPO_ENDERECO.values());
        mv.addObject(cliente);
        return mv;
    }

    @PostMapping("/salvarEndereco")
    public ModelAndView salvaEndereco(@PathVariable("id") Cliente cliente, @Valid EnderecoDto enderecoDto, BindingResult result) {

        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return paginaCriaEnderecoCheckout(cliente, enderecoDto, mv);
        }

        mv.setViewName("redirect:/cliente/perfil/" + cliente.getId() + "/checkout");

        Endereco endereco = enderecoDto.toEndereco();
        endereco.setCliente(cliente);
        Endereco novoEndereco = enderecoService.salvar(endereco);

        mv.addObject("resultados", "Endereco salvo com sucesso!!");
        return mv;
    }

    @GetMapping("/salvarCartao")
    private ModelAndView paginaCriaCartaoCheckout(Cliente cliente, CartaoCreditoDto cartaoCreditoDto, ModelAndView mv) {
        if(mv == null){
            mv = new ModelAndView();
        }
        mv.setViewName("pedido/formCartao");
        mv.addObject("bandeiras", BANDEIRA.values());
        mv.addObject(cliente);
        return mv;
    }

    @PostMapping("/salvarCartao")
    public ModelAndView salvaCartao(@PathVariable("id") Cliente cliente, @Valid CartaoCreditoDto cartaoCreditoDto, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return paginaCriaCartaoCheckout(cliente, cartaoCreditoDto, mv);
        }
        mv.setViewName("redirect:/cliente/perfil/" + cliente.getId() + "/checkout");
        CartaoCredito cartaoCredito = cartaoCreditoDto.toCartao();

        cartaoCredito.setCliente(cliente);
        CartaoCredito cartaoResponse = cartaoService.salvar(cartaoCredito);
        mv.addObject("resultados", "Cartao salvo com sucesso!!");
        return mv;
    }

}

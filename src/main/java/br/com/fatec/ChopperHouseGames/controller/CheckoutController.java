package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.*;
import br.com.fatec.ChopperHouseGames.dto.CartaoCreditoDto;
import br.com.fatec.ChopperHouseGames.dto.EnderecoDto;
import br.com.fatec.ChopperHouseGames.service.ICarrinhoService;
import br.com.fatec.ChopperHouseGames.service.ICartaoService;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import br.com.fatec.ChopperHouseGames.service.IEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/cliente/perfil/{id}/checkout")
public class CheckoutController {

    @Autowired
    IClienteService clienteService;

    @Autowired
    ICarrinhoService carrinhoService;

    @Autowired
    IEnderecoService enderecoService;

    @Autowired
    ICartaoService cartaoService;

    @GetMapping
    public ModelAndView checkout(@PathVariable("id") Cliente cliente, Pedido pedido) {

        double totalPedido = cliente.getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum();

        ModelAndView mv = new ModelAndView("pedido/checkout");
        mv.addObject("cliente", cliente);
        mv.addObject("totalProdutos", cliente.getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum());
        mv.addObject("totalPedido", totalPedido + 15);
        mv.addObject("pedido", pedido);
        mv.addObject("cartaoCredito", new CartaoCredito());
        mv.addObject("endereco", new Endereco());
        mv.addObject("tipoEnderecos", TIPO_ENDERECO.values());
        mv.addObject("bandeiras", BANDEIRA.values());

        clienteService.usuarioLogado(cliente.getId(), mv);
        return mv;
    }

    @GetMapping("finaliza-pedido")
    public ModelAndView finishCheckout(@PathVariable("id") Cliente cliente) {
        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/pedidos");

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
    public ModelAndView removeItemCarrinho(Cliente cliente, Integer id){
        ModelAndView mv = new ModelAndView("pedido/checkout");


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

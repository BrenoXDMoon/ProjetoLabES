package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.core.domain.entity.BANDEIRA;
import br.com.fatec.chopperhousegames.inbound.facade.CartaoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.EnderecoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.CartaoCreditoDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

//TODO: REFATORAR PASSANDO FACADE EM TODOS OS MÉTODOS COMENTADOS

@Controller
@RequestMapping("/cliente/perfil/{id}/checkout")
public class CheckoutController {

    private final CartaoFacade cartaoFacade;
    private EnderecoFacade enderecoFacade;

    public CheckoutController(CartaoFacade cartaoFacade) {
        this.cartaoFacade = cartaoFacade;
    }

//    @GetMapping
//    public ModelAndView checkout(@PathVariable("id") ClienteDTO clienteDto, Pedido pedidoDto, ModelAndView mv) {
//
//        Double totalPedido = cliente.getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum();
//
//        if(mv == null){
//            mv = new ModelAndView();
//        }
//
//        pedido = new Pedido();
//        mv.setViewName("pedido/checkout");
//        mv.addObject("cliente", cliente);
//        mv.addObject("totalProdutos", cliente.getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum());
//        mv.addObject("totalPedido", totalPedido + 15);
//        pedido.setTotal(totalPedido);
//        mv.addObject("pedido", new Pedido());
//        mv.addObject("cartaoCredito", new CartaoCredito());
//        mv.addObject("endereco", new Endereco());
//        mv.addObject("tipoEnderecos", TIPO_ENDERECO.values());
//        mv.addObject("bandeiras", BANDEIRA.values());
//        mv.addObject("cupons", cupomService.listarCupomDesconto());
//        mv.addObject("cupomZerado", cupomService.buscarCupomZerado());
//        mv.addObject("cuponsTroca", cupomService.listarCupomTroca());
//
//        clienteService.usuarioLogado(cliente.getId(), mv);
//        return mv;
//    }
//
//    @PostMapping
//    public ModelAndView finalizarPedido(@PathVariable("id") ClienteDTO cliente, @Valid Pedido pedido, BindingResult result) {
//        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/pedidos");
//
//        System.out.println(pedido.getTotal());
//
//        pedido.setCliente(clienteService.atualUsuarioLogado());
//        pedidoService.salvar(pedido, result);
//
//        if(result.hasErrors()){
//            mv.addObject("resultados", result);
//            return checkout(cliente, pedido, mv);
//        }
//
//        return mv;
//    }
//
//    @PostMapping("/adicionarJogo")
//    public ModelAndView adicionaItemCarrinho(@PathVariable("id") ClienteDTO cliente, Integer idJogo, Integer quantidade) {
//        if(!clienteService.usuarioEstaLogado(cliente.getId())){
//            return new ModelAndView("redirect:/");
//        }
//        carrinhoService.adicionarItemCarrinho(cliente, idJogo, quantidade);
//
//        cliente = clienteService.atualUsuarioLogado();
//        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/checkout");
//        mv.addObject("cliente", cliente);
//
//        return mv;
//    }
//
//    @PostMapping("removeItem")
//    public ModelAndView removeItemCarrinho(@PathVariable("id") ClienteDTO cliente, Integer id){
//        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "/checkout");
//        cliente = clienteService.atualUsuarioLogado();
//        carrinhoService.removerItemCarrinho(cliente, id);
//        mv.addObject("cliente", cliente);
//        return mv;
//    }

//    @GetMapping("/salvarEndereco")
//    public ModelAndView paginaCriaEnderecoCheckout(@PathVariable("id") ClienteDTO cliente, EnderecoDTO enderecoDto, ModelAndView mv){
//        if(mv == null){
//            mv = new ModelAndView();
//        }
//        mv.setViewName("pedido/formEndereco");
//        mv.addObject("tiposEnd", TIPO_ENDERECO.values());
//        mv.addObject(cliente);
//        return mv;
//    }

//    @PostMapping("/salvarEndereco")
//    public ModelAndView salvaEndereco(@PathVariable("id") ClienteDTO cliente, @Valid EnderecoDTO enderecoDto, BindingResult result) {
//
//        ModelAndView mv = new ModelAndView();
//        if(result.hasErrors()){
//            mv.addObject("resultados", result);
//            return paginaCriaEnderecoCheckout(cliente, enderecoDto, mv);
//        }
//
//        mv.setViewName("redirect:/cliente/perfil/" + cliente.getId() + "/checkout");
//        enderecoFacade.salvar(cliente, enderecoDto);
//
//        mv.addObject("resultados", "Endereco salvo com sucesso!!");
//        return mv;
//    }

    @GetMapping("/salvarCartao")
    private ModelAndView paginaCriaCartaoCheckout(ClienteDTO clienteDto, CartaoCreditoDTO dto, ModelAndView mv) {
        if(mv == null){
            mv = new ModelAndView();
        }
        mv.setViewName("pedido/formCartao");
        mv.addObject("bandeiras", BANDEIRA.values());
        mv.addObject(clienteDto);
        return mv;
    }

    @PostMapping("/salvarCartao")
    public ModelAndView salvaCartao(@PathVariable("id") ClienteDTO clienteDto, @Valid CartaoCreditoDTO dto, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + clienteDto.getId() + "/checkout");
        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return paginaCriaCartaoCheckout(clienteDto, dto, mv);
        }

        cartaoFacade.salvar(dto);
        mv.addObject("resultados", "Cartao salvo com sucesso!!");
        return mv;
    }

}

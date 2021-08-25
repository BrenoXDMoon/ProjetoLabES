package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.*;
import br.com.fatec.ChopperHouseGames.dto.request.CartaoCreditoDtoForm;
import br.com.fatec.ChopperHouseGames.dto.request.EnderecoDtoForm;
import br.com.fatec.ChopperHouseGames.facade.impl.Facade;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.service.ICartaoService;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/cliente/perfil")
public class CartaoCreditoController {

    private Facade facade;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    private ICartaoService cartaoService;

    @GetMapping("/{id}/cartoes")
    public ModelAndView listarCartoes(@PathVariable("id") Cliente cliente, CartaoCreditoDtoForm dto) {

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        ModelAndView mv = new ModelAndView("/cliente/listaCartoes");

        facade.listar(new CartaoCredito());

        mv.addObject("cliente",cliente);
        clienteService.usuarioLogado(cliente.getId(), mv);
        return mv;
    }

    @GetMapping("/{id}/cartoes/novo")
    public ModelAndView formularioNovoCartao(@PathVariable("id") Cliente cliente, CartaoCreditoDtoForm cartaoCreditoDtoForm){

        ModelAndView mv = new ModelAndView("cliente/cartao/form");

        mv.addObject("cliente",cliente);
        mv.addObject("bandeiras", BANDEIRA.values());

        return mv;
    }

    @PostMapping("/{id}/cartoes/novo")
    public ModelAndView salvaCartao(@PathVariable("id") Cliente cliente, @Valid CartaoCreditoDtoForm cartaoCreditoDtoForm, BindingResult result, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView();
        CartaoCredito cartao = cartaoCreditoDtoForm.toCartao();

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        cliente = clienteService.atualUsuarioLogado();
        cartao.setCliente(cliente);
        cliente.getCartoes().add(cartao);
        facade.salvar(cartao);

        mv.addObject("cliente", cliente);
        mv.setViewName("cliente/perfil");

        return mv;
    }

    @PostMapping("/{id}/cartoes")
    public ModelAndView excluirCartao(@RequestParam String id, RedirectAttributes attributes){

        ModelAndView mv = new ModelAndView("/cliente/perfil");

        CartaoCredito cartaoCredito = cartaoService.buscarById(Integer.parseInt(id));

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        facade.excluir(cartaoCredito);

        Cliente cliente = clienteService.atualUsuarioLogado();

        mv.addObject("cliente", cliente);

        return mv;
    }

    @GetMapping("/{id}/cartoes/editar/{idEnd}")
    public ModelAndView formularioEditar(@PathVariable("id") Cliente cliente,
                                         @PathVariable("idEnd") CartaoCredito idEnd,
                                         @Valid EnderecoDtoForm enderecoDtoForm,
                                         BindingResult result, RedirectAttributes attributes){

        ModelAndView mv = new ModelAndView("/cliente/cartao/formEditar");
        CartaoCredito cartaoCredito = cartaoService.buscarById(idEnd.getId());
        cliente = clienteService.atualUsuarioLogado();

        mv.addObject("cliente", cliente);
        mv.addObject("cartao", cartaoCredito);
        mv.addObject("bandeiras", BANDEIRA.values());

        return mv;
    }

    @PostMapping("/{id}/cartoes/editar")
    public ModelAndView editaEndereco(@Valid CartaoCredito cartaoCreditoForm, RedirectAttributes attributes){

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        Cliente cliente = clienteService.atualUsuarioLogado();

        cartaoCreditoForm.setCliente(cliente);

        CartaoCredito cartaoCredito = (CartaoCredito) facade.editar(cartaoCreditoForm).getEntidade();


        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", cliente);
        attributes.addFlashAttribute("mensagem", "Cartao atualizado com sucesso!");

        return mv;
    }
}

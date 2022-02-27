package br.com.fatec.ChopperHouseGames.inbound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente/perfil")
public class CartaoCreditoController {

//    private Facade facade;
//
//    @Autowired
//    private ClienteService clienteService;
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Autowired
//    private EnderecoRepository enderecoRepository;
//
//    @Autowired
//    private CartaoCreditoRepository cartaoCreditoRepository;
//
//    @Autowired
//    private CartaoService cartaoService;
//
//    @GetMapping("/{id}/cartoes")
//    public ModelAndView listarCartoes(@PathVariable("id") Cliente cliente, CartaoCreditoDto dto) {
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//
//        ModelAndView mv = new ModelAndView("/cliente/listaCartoes");
//
//        facade.listar(new CartaoCredito());
//
//        mv.addObject("cliente",cliente);
//        clienteService.usuarioLogado(cliente.getId(), mv);
//        return mv;
//    }
//
//    @GetMapping("/{id}/cartoes/novo")
//    public ModelAndView formularioNovoCartao(@PathVariable("id") Cliente cliente, CartaoCreditoDto cartaoCreditoDto){
//
//        ModelAndView mv = new ModelAndView("cliente/cartao/form");
//
//        mv.addObject("cliente",cliente);
//        mv.addObject("bandeiras", BANDEIRA.values());
//
//        return mv;
//    }
//
//    @PostMapping("/{id}/cartoes/novo")
//    public ModelAndView salvaCartao(@PathVariable("id") Cliente cliente, @Valid CartaoCreditoDto cartaoCreditoDto, BindingResult result, RedirectAttributes attributes){
//        ModelAndView mv = new ModelAndView();
//        CartaoCredito cartao = cartaoCreditoDto.toCartao();
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//        cliente = clienteService.atualUsuarioLogado();
//        cartao.setCliente(cliente);
//        cliente.getCartoes().add(cartao);
//        facade.salvar(cartao);
//
//        mv.addObject("cliente", cliente);
//        mv.setViewName("cliente/perfil");
//        mv.addObject("mensagem", "Cartao atualizado com sucesso!");
//
//        return mv;
//    }
//
//    @PostMapping("/{id}/cartoes")
//    public ModelAndView excluirCartao(@RequestParam String id, RedirectAttributes attributes){
//
//        ModelAndView mv = new ModelAndView("/cliente/perfil");
//
//        CartaoCredito cartaoCredito = cartaoService.buscarById(Integer.parseInt(id));
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//        facade.excluir(cartaoCredito);
//
//        Cliente cliente = clienteService.atualUsuarioLogado();
//
//        mv.addObject("cliente", cliente);
//
//        mv.addObject("mensagem", "Cartao removido com sucesso!");
//
//        return mv;
//    }
//
//    @GetMapping("/{id}/cartoes/editar/{idCard}")
//    public ModelAndView formularioEditar(@PathVariable("id") Cliente cliente, @PathVariable("idCard") Integer idEnd, ClienteDto clienteDto){
//
//        ModelAndView mv = new ModelAndView("/cliente/cartao/formEditar");
//        CartaoCredito cartaoCredito = cartaoService.buscarById(idEnd);
//        cliente = clienteService.atualUsuarioLogado();
//
//        mv.addObject("cliente", cliente);
//        mv.addObject("cartao", cartaoCredito);
//        mv.addObject("bandeiras", BANDEIRA.values());
//
//        return mv;
//    }
//
//    @PostMapping("/{id}/cartoes/editar")
//    public ModelAndView editaEndereco(@Valid CartaoCredito cartaoCreditoForm, RedirectAttributes attributes, ClienteDto clienteDto){
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//
//        Cliente cliente = clienteService.atualUsuarioLogado();
//
//        cartaoCreditoForm.setCliente(cliente);
//
//        CartaoCredito cartaoCredito = (CartaoCredito) facade.editar(cartaoCreditoForm).getEntidade();
//
//
//        ModelAndView mv = new ModelAndView("/cliente/perfil");
//        mv.addObject("cliente", cliente);
//        mv.addObject("mensagem", "Cartao atualizado com sucesso!");
//
//        return mv;
//    }
}

package br.com.fatec.ChopperHouseGames.inbound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente/perfil")
public class EnderecoController {

//    @Autowired
//    private ClienteService clienteService;
//
//    @Autowired
//    ClienteRepository clienteRepository;
//
//    @Autowired
//    EnderecoRepository enderecoRepository;
//
//    @Autowired
//    CartaoCreditoRepository cartaoCreditoRepository;
//
//    @Autowired
//    private Facade facade;
//
//    @Autowired
//    private EnderecoService enderecoService;
//
//    @GetMapping("/{id}/enderecos")
//    public ModelAndView listarEnderecos(@PathVariable("id") Cliente cliente) {
//        ModelAndView mv = new ModelAndView("/cliente/listaEnderecos");
//
//        facade.salvar(cliente);
//
//        mv.addObject("cliente",cliente);
//        clienteService.usuarioLogado(cliente.getId(), mv);
//        return mv;
//    }
//
//    @GetMapping("/{id}/enderecos/novo")
//    public ModelAndView formularioNovoEndereco(@PathVariable("id") Cliente cliente, EnderecoDto enderecoDto){
//        ModelAndView mv = new ModelAndView("cliente/endereco/form");
//        mv.addObject("cliente",cliente);
//        mv.addObject("endereco", enderecoDto);
//        mv.addObject("tiposEnd", TIPO_ENDERECO.values());
//        return mv;
//    }
//
//    @PostMapping("/{id}/enderecos/novo")
//    public ModelAndView salvaEndereco(@PathVariable("id") Cliente cliente, @Valid EnderecoDto enderecoDto, BindingResult result, RedirectAttributes attributes){
//        ModelAndView mv = new ModelAndView();
//        Endereco endereco = enderecoDto.toEndereco();
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//        cliente = clienteService.atualUsuarioLogado();
//        endereco.setCliente(cliente);
//        cliente.getEnderecos().add(endereco);
//        facade.salvar(endereco);
//        mv.addObject("cliente", cliente);
//
//        mv.setViewName("/cliente/listaEnderecos");
//
//        mv.addObject("mensagem", "Endereco criado com sucesso!");
//
//        return mv;
//    }
//
//    @PostMapping("/{id}/enderecos")
//    public ModelAndView excluirEndereco(@RequestParam String id, RedirectAttributes attributes, ClienteDTO clienteDto){
//
//        ModelAndView mv = new ModelAndView("/cliente/perfil");
//
//        Endereco endereco = enderecoService.buscarPorId(Integer.parseInt(id));
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//        facade.excluir(endereco);
//
//        Cliente cliente = clienteService.atualUsuarioLogado();
//
//        mv.addObject("cliente", cliente);
//
//        mv.addObject("mensagem", "Endereco removido com sucesso!");
//
//        return mv;
//    }
//    @GetMapping("/{id}/enderecos/editar/{idEnd}")
//    public ModelAndView formularioEditar(@PathVariable("id") Cliente cliente, @PathVariable("idEnd") Endereco idEnd, EnderecoDto enderecoDto){
//
//        ModelAndView mv = new ModelAndView("/cliente/endereco/formEditar");
//
//        Endereco endereco = enderecoService.buscarPorId(idEnd.getId());
//
//        cliente = clienteService.atualUsuarioLogado();
//
//        mv.addObject("cliente", cliente);
//        mv.addObject("endereco", endereco);
//        mv.addObject("tiposEnd", TIPO_ENDERECO.values());
//
//        return mv;
//    }
//
//    @PostMapping("/{id}/enderecos/editar")
//    public ModelAndView editaEndereco(@Valid Endereco enderecoForm, RedirectAttributes attributes){
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//        Cliente cliente = clienteService.atualUsuarioLogado();
//
//        enderecoForm.setCliente(cliente);
//
//        Endereco endereco = (Endereco) facade.editar(enderecoForm).getEntidade();
//
//
//
//        ModelAndView mv = new ModelAndView("/cliente/perfil");
//        mv.addObject("cliente", cliente);
//
//        mv.addObject("mensagem", "Endereco atualizado com sucesso!");
//
//        return mv;
//    }
}
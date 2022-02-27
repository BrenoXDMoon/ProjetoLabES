package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.inbound.facade.ClienteFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.validator.ClienteValidator;
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
@RequestMapping("cliente")
public class ClienteController {

    ClienteFacade facade;

    ClienteService service;

    ClienteValidator validator;

    @Autowired
    public ClienteController(ClienteFacade facade, ClienteService service, ClienteValidator validator) {
        this.facade = facade;
        this.service = service;
        this.validator = validator;
    }

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
//    IPedidoService pedidoService;
//
//    @Autowired
//    ITipoClienteService tipoClienteService;
//
//    @Autowired
//    IDevolucaoService devolucaoService;

    @GetMapping("/novo")
    public ModelAndView novoCliente(ClienteDTO dto, ModelAndView mv) {
        if (mv == null) {
            mv = new ModelAndView();
        }
        mv.setViewName("/cliente/form");
        mv.addObject("dto", dto);

        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvarCliente(@Valid ClienteDTO dto, BindingResult result) {

        ModelAndView mv = new ModelAndView();


        if (validator.validaFormularioCadastro(dto, result).hasErrors()) {
            System.out.println(result.getAllErrors());
            mv.addObject("resultados", result);
            return novoCliente(dto, mv);
        }

        facade.salvar(dto);

        mv.setViewName("redirect:/");

        mv.addObject("cliente", dto);

        mv.addObject("mensagem", "Usuário criado com sucesso!");

        return mv;
    }

    @GetMapping("/perfil/{id}")
    public ModelAndView perfil(@PathVariable("id") Cliente cliente) {

        ModelAndView mv = new ModelAndView();
        cliente = service.atualUsuarioLogado();
        if (service.validaRoleUsuario(cliente)) {
            mv.setViewName("/cliente/perfil");
            mv.addObject("cliente", cliente);
        } else {
            mv.setViewName("/admin/dashboard");
            mv.addObject("admin", cliente);
        }
        return mv;
    }

//    @PostMapping("editar")
//    public ModelAndView editarCliente(@ModelAttribute("cliente") Cliente clienteDto, BindingResult result){
//
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//
//        Cliente cliente = (Cliente) facade.editar(clienteDto).getEntidade();
//
//        ModelAndView mv = new ModelAndView("/cliente/perfil");
//        mv.addObject("cliente", cliente);
//
//        mv.addObject("mensagem", "Usuário atualizado com sucesso!");
//
//        return mv;
//    }
//
//    @GetMapping("/perfil/{id}/senha")
//    public ModelAndView editarSenha(@PathVariable("id") Cliente cliente, SenhaDto senhaDto, ModelAndView mv){
//        if(mv == null){
//            mv = new ModelAndView();
//        }
//
//        mv.setViewName("/cliente/senha");
//        cliente = service.atualUsuarioLogado();
//        mv.addObject(cliente);
//
//        return mv;
//    }
//
//    @PostMapping("/perfil/{id}/senha")
//    public ModelAndView editarSenha(@Valid SenhaDto senhaDto, BindingResult result){
//        ModelAndView mv = new ModelAndView();
//        Cliente cliente = service.atualUsuarioLogado();
//        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
//
//        if(!senhaDto.senhaAntigaCorreta(cliente)){
//            result.addError(new ObjectError("resultado","A senha antiga não confere"));
//        }
//
//        if(!senhaDto.confirmaSenha()){
//            result.addError(new ObjectError("resultado","A senha nova não confere com a confirmação"));
//        }
//
//        if(result.hasErrors()){
//            mv.addObject("resultados", result);
//            return editarSenha(cliente,senhaDto, mv);
//        }
//
//        cliente.setSenha(senhaDto.toSenha());
//
//        cliente = (Cliente) facade.editar(cliente).getEntidade();
//
//        mv.setViewName("/cliente/perfil");
//        mv.addObject("cliente", cliente);
//
//        mv.addObject("mensagem", "Senha atualizada com sucesso!");
//
//        return mv;
//    }
//
//    @GetMapping("perfil/{id}/devolucao/{idPed}")
//    public ModelAndView acessarFormularioDevolucao(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido){
//
//        ModelAndView mv = new ModelAndView("cliente/pedido/formDevolucao");
//
//        mv.addObject("cliente", service.atualUsuarioLogado());
//        DevolucaoDto devolucaoDto = new DevolucaoDto();
//        devolucaoDto.setPedido(pedidoService.buscarById(pedido.getId()));
//        mv.addObject("dto", devolucaoDto);
//
//        return mv;
//    }
//
//    @PostMapping("perfil/{id}/devolucao/{idPed}")
//    public ModelAndView solicitarDevolucaoPedido(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido, @ModelAttribute DevolucaoDto dto){
//        ModelAndView mv = new ModelAndView("cliente/perfil");
//
//        Devolucao devolucao = new Devolucao();
//        devolucao.setMotivo(dto.getMotivo());
//        devolucao.setStatusDevolucao(dto.getStatusDevolucao());
//        pedido = pedidoService.buscarById(pedido.getId());
//
//        Integer i = 0;
//        for(Item item : pedido.getItens()){
//            item.setQuantidadeTroca(dto.getPedido().getItens().get(i).getQuantidadeTroca());
//            i++;
//        }
//        devolucao.setPedido(pedido);
//
//        devolucaoService.salvarSolicitacaoDevolucao(devolucao);
//
//        mv.addObject("cliente", service.atualUsuarioLogado());
//        mv.addObject("mensagem", "Solicitação de troca enviada com sucesso!");
//
//        return mv;
//    }
//
//    @GetMapping("perfil/{id}/pedidos/visualizar/{idPed}")
//    public ModelAndView visualizarPedido(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido) {
//        ModelAndView mv = new ModelAndView("cliente/pedido/detalhe");
//
//        mv.addObject("cliente", cliente);
//        mv.addObject("pedido", pedidoService.buscarById(pedido.getId()));
//        mv.addObject("devolucaoExist", devolucaoService.buscaDevolucaoByPedidoId(pedido));
//        mv.addObject("devolucao", new Devolucao());
//
//        return mv;
//    }
}
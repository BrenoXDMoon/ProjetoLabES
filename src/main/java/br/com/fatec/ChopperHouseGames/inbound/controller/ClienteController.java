package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.Devolucao;
import br.com.fatec.ChopperHouseGames.core.domain.Item;
import br.com.fatec.ChopperHouseGames.core.domain.Pedido;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDto;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.DevolucaoDto;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.SenhaDto;
import br.com.fatec.ChopperHouseGames.inbound.facade.IFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.impl.Facade;
import br.com.fatec.ChopperHouseGames.core.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.core.service.IClienteService;
import br.com.fatec.ChopperHouseGames.core.service.IDevolucaoService;
import br.com.fatec.ChopperHouseGames.core.service.IPedidoService;
import br.com.fatec.ChopperHouseGames.core.service.ITipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    IFacade facade;

    @Autowired
    IClienteService service;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    IPedidoService pedidoService;

    @Autowired
    ITipoClienteService tipoClienteService;

    @Autowired
    IDevolucaoService devolucaoService;

    @GetMapping("/novo")
    public ModelAndView novoCliente(ClienteDto clienteForm, ModelAndView mv){

        if(mv == null){
            mv = new ModelAndView();
        }
        mv.setViewName("/cliente/form");
        mv.addObject("clienteForm", clienteForm);

        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvarCliente(@Valid ClienteDto clienteDto, BindingResult result){

        ModelAndView mv = new ModelAndView();
        if(!clienteDto.confirmaSenha()){
            result.addError(new ObjectError("resultado", "Senha não confere com a confirmação de senha"));
        }
        if(!clienteDto.validaSenha()){
            result.addError(new ObjectError("resultado", "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }
        if(!clienteDto.validaEmail(clienteRepository, clienteDto.getEmail())){
            result.addError(new ObjectError("resultado", "Email já cadastrado"));
        }

        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            mv.addObject("resultados", result);
            return novoCliente(clienteDto, mv);
        }

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        Cliente cliente = clienteDto.toCliente();

        cliente.setTipoCliente(tipoClienteService.buscarById(1));

        cliente.setRoles("CLIENTE");

        facade.salvar(cliente);

        mv.setViewName("redirect:/");

        mv.addObject("cliente", cliente);

        mv.addObject("mensagem", "Usuário criado com sucesso!");

        return mv;
    }

    @GetMapping("/perfil/{id}")
    public ModelAndView perfil(@PathVariable("id") Cliente cliente) {

        ModelAndView  mv = new ModelAndView();
        cliente = service.atualUsuarioLogado();
        if(service.validaRoleUsuario(cliente)){
            mv.setViewName("/cliente/perfil");
            mv.addObject("cliente", cliente);
        }else{
            mv.setViewName("/admin/dashboard");
            mv.addObject("admin", cliente);
        }
        return mv;
    }

    @PostMapping("editar")
    public ModelAndView editarCliente(@ModelAttribute("cliente") Cliente clienteDto, BindingResult result){

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        Cliente cliente = (Cliente) facade.editar(clienteDto).getEntidade();

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", cliente);

        mv.addObject("mensagem", "Usuário atualizado com sucesso!");

        return mv;
    }

    @GetMapping("/perfil/{id}/senha")
    public ModelAndView editarSenha(@PathVariable("id") Cliente cliente, SenhaDto senhaDto, ModelAndView mv){
        if(mv == null){
            mv = new ModelAndView();
        }

        mv.setViewName("/cliente/senha");
        cliente = service.atualUsuarioLogado();
        mv.addObject(cliente);

        return mv;
    }

    @PostMapping("/perfil/{id}/senha")
    public ModelAndView editarSenha(@Valid SenhaDto senhaDto, BindingResult result){
        ModelAndView mv = new ModelAndView();
        Cliente cliente = service.atualUsuarioLogado();
        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        if(!senhaDto.senhaAntigaCorreta(cliente)){
            result.addError(new ObjectError("resultado","A senha antiga não confere"));
        }

        if(!senhaDto.confirmaSenha()){
            result.addError(new ObjectError("resultado","A senha nova não confere com a confirmação"));
        }

        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return editarSenha(cliente,senhaDto, mv);
        }

        cliente.setSenha(senhaDto.toSenha());

        cliente = (Cliente) facade.editar(cliente).getEntidade();

        mv.setViewName("/cliente/perfil");
        mv.addObject("cliente", cliente);

        mv.addObject("mensagem", "Senha atualizada com sucesso!");

        return mv;
    }

    @GetMapping("perfil/{id}/devolucao/{idPed}")
    public ModelAndView acessarFormularioDevolucao(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido){

        ModelAndView mv = new ModelAndView("cliente/pedido/formDevolucao");

        mv.addObject("cliente", service.atualUsuarioLogado());
        DevolucaoDto devolucaoDto = new DevolucaoDto();
        devolucaoDto.setPedido(pedidoService.buscarById(pedido.getId()));
        mv.addObject("dto", devolucaoDto);

        return mv;
    }

    @PostMapping("perfil/{id}/devolucao/{idPed}")
    public ModelAndView solicitarDevolucaoPedido(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido, @ModelAttribute DevolucaoDto dto){
        ModelAndView mv = new ModelAndView("cliente/perfil");

        Devolucao devolucao = new Devolucao();
        devolucao.setMotivo(dto.getMotivo());
        devolucao.setStatusDevolucao(dto.getStatusDevolucao());
        pedido = pedidoService.buscarById(pedido.getId());

        Integer i = 0;
        for(Item item : pedido.getItens()){
            item.setQuantidadeTroca(dto.getPedido().getItens().get(i).getQuantidadeTroca());
            i++;
        }
        devolucao.setPedido(pedido);

        devolucaoService.salvarSolicitacaoDevolucao(devolucao);

        mv.addObject("cliente", service.atualUsuarioLogado());
        mv.addObject("mensagem", "Solicitação de troca enviada com sucesso!");

        return mv;
    }

    @GetMapping("perfil/{id}/pedidos/visualizar/{idPed}")
    public ModelAndView visualizarPedido(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido) {
        ModelAndView mv = new ModelAndView("cliente/pedido/detalhe");

        mv.addObject("cliente", cliente);
        mv.addObject("pedido", pedidoService.buscarById(pedido.getId()));
        mv.addObject("devolucaoExist", devolucaoService.buscaDevolucaoByPedidoId(pedido));
        mv.addObject("devolucao", new Devolucao());

        return mv;
    }
}
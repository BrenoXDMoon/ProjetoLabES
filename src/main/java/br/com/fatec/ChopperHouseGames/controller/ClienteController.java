package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.domain.Devolucao;
import br.com.fatec.ChopperHouseGames.dto.ClienteDto;
import br.com.fatec.ChopperHouseGames.dto.SenhaDto;
import br.com.fatec.ChopperHouseGames.facade.IFacade;
import br.com.fatec.ChopperHouseGames.facade.impl.Facade;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import br.com.fatec.ChopperHouseGames.service.ITipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    ITipoClienteService tipoClienteService;

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
    public ModelAndView salvarCliente(@Valid ClienteDto clienteDto, BindingResult result, RedirectAttributes attributes){

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

        mv.setViewName("index");

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
    public ModelAndView editaCliente(@ModelAttribute("cliente") Cliente clienteDto, BindingResult result){

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        Cliente cliente = (Cliente) facade.editar(clienteDto).getEntidade();

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", cliente);

        mv.addObject("mensagem", "Usuário atualizado com sucesso!");

        return mv;
    }

    @GetMapping("/perfil/{id}/senha")
    public ModelAndView editaSenha(@PathVariable("id") Cliente cliente, SenhaDto senhaDto, ModelAndView mv){
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
            return editaSenha(cliente,senhaDto, mv);
        }

        cliente.setSenha(senhaDto.toSenha());

        cliente = (Cliente) facade.editar(cliente).getEntidade();

        mv.setViewName("/cliente/perfil");
        mv.addObject("cliente", cliente);

        mv.addObject("mensagem", "Senha atualizada com sucesso!");

        return mv;
    }

    @GetMapping("perfil/{id}/pedidos")
    public ModelAndView listaPedidos(@PathVariable("id") Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/pedido/lista");
        //gerar pedidos reais
        mv.addObject("cliente", cliente);
        mv.addObject("devolucao", new Devolucao());

        return mv;
    }

    @GetMapping("perfil/{id}/pedidos/visualizar")
    public ModelAndView visualizaPedido(@PathVariable("id") Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/pedido/detalhe");
        //jogar pedido pro front
        mv.addObject("cliente", cliente);
        mv.addObject("devolucao", new Devolucao());

        return mv;
    }
}
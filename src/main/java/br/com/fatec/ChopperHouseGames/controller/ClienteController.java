package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.dto.request.ClienteDtoForm;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView novoCliente(ClienteDtoForm clienteForm){

        ModelAndView mv = new ModelAndView("/cliente/form");
        mv.addObject("clienteForm", clienteForm);

        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvarCliente(@Valid ClienteDtoForm clienteForm, BindingResult result, RedirectAttributes attributes){

        System.out.println("Vamo validar");
        if(!clienteForm.confirmaSenha()){
            result.addError(new ObjectError("cliente", "Senha é obrigatória"));
        }
        System.out.println("Senhas conferem");
        if(!clienteForm.validaSenha()){
            result.addError(new ObjectError("cliente", "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }
        System.out.println("Serase senha é forte");

        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return novoCliente(clienteForm);
        }

        System.out.println("não teve erro, bora salvar com coragem");

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        Cliente cliente = clienteForm.toCliente();

        cliente.setTipoCliente(tipoClienteService.buscarById(1));

        facade.salvar(cliente);

        ModelAndView mv = new ModelAndView("/");
        mv.addObject("cliente", cliente);

        attributes.addFlashAttribute("mensagem", "Usuário criado com sucesso!");

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
    public ModelAndView editaCliente(ClienteDtoForm clienteForm, BindingResult result, RedirectAttributes attributes){

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);

        Cliente cliente = (Cliente) facade.editar(clienteForm.toClienteEdit()).getEntidade();

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", cliente);

        attributes.addFlashAttribute("message", "Alteração realizada com sucesso!");

        return mv;
    }
}
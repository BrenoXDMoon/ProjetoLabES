package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.dto.request.ClienteDtoForm;
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

    @Autowired
    private IClienteService service;

    @GetMapping("/novo")
    public ModelAndView novoCliente(ClienteDtoForm clienteForm){

        ModelAndView mv = new ModelAndView("/cliente/form");
        mv.addObject("clienteForm", clienteForm);

        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvarCliente(@Valid ClienteDtoForm clienteForm, BindingResult result, RedirectAttributes attributes){

        if(!clienteForm.confirmaSenha()){
            result.addError(new ObjectError("cliente", "Senha é obrigatória"));
        }

        if(!clienteForm.validaSenha()){
            result.addError(new ObjectError("cliente", "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }

        if(result.hasErrors()){
            return novoCliente(clienteForm);
        }

        Cliente cliente = clienteForm.toCliente();
        service.salvar(cliente);

        ModelAndView mv = new ModelAndView("redirect:/cliente/perfil/" + cliente.getId() + "");
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

        Cliente cliente = service.editar(clienteForm.toClienteEdit());

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", cliente);

        System.out.println("CLIENTE ALTERADO COM SUCESSO");

        attributes.addFlashAttribute("message", "Alteração realizada com sucesso!");

        return mv;
    }
}

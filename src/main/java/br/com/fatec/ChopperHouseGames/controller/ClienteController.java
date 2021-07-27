package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.dto.request.ClienteForm;
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

    @Autowired
    private ITipoClienteService iTipoClienteService;

    @GetMapping("/novo")
    public ModelAndView novoCliente(ClienteForm clienteForm){

        ModelAndView mv = new ModelAndView("/cliente/form");
        mv.addObject("clienteForm", clienteForm);

        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvarCliente(@Valid ClienteForm clienteForm, BindingResult result, RedirectAttributes attributes){

        Cliente cliente = new Cliente();

        if(!clienteForm.confirmaSenha()){
            result.addError(new ObjectError("cliente", "Senha é obrigatória"));
        }

        if(!clienteForm.validaSenha()){
            result.addError(new ObjectError("cliente", "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 6 e 20"));
        }

        if(result.hasErrors()){
            return novoCliente(clienteForm);
        }

        cliente.setTipoCliente(iTipoClienteService.buscarById(1));//1 é o id do Cliente mais básico que tenho

        service.salvar(cliente);

        ModelAndView mv = new ModelAndView("redirect:/customer/edit/" + cliente.getId() + "");

        attributes.addFlashAttribute("message", "Usuário criado com sucesso!");

        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable("id") Cliente cliente) {

        ModelAndView mv = new ModelAndView("/cliente/perfil");

        service.usuarioLogado(cliente.getId(), mv);



        return mv;
    }
}

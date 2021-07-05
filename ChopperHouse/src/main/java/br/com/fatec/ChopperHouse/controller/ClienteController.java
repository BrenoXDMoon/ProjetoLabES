package br.com.fatec.chopperhouse.controller;


import br.com.fatec.chopperhouse.command.*;
import br.com.fatec.chopperhouse.dto.request.ClienteDTO;
import br.com.fatec.chopperhouse.models.Cliente;
import br.com.fatec.chopperhouse.models.Resultado;
import br.com.fatec.chopperhouse.models.mapper.ClienteMapper;
import br.com.fatec.chopperhouse.service.ClienteSalvarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    AtivarCommand ativarCommand;

    @Autowired
    BuscarByIdCommand buscarByIdCommand;

    @Autowired
    EditarCommand editarCommand;

    @Autowired
    ExcluirCommand excluirCommand;

    @Autowired
    ListarCommand listarCommand;

    @Autowired
    SalvarCommand salvarCommand;

    @GetMapping("form")
    public String formulario(ClienteDTO requisicao){
        System.out.println("Entrou no formulário");
        return "cliente/form";
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid ClienteDTO dto, BindingResult result){
        System.out.println("Tentando salvar");
        ModelAndView mv = null;
        mv = new ModelAndView("index");
        Cliente cliente = ClienteMapper.toCliente(dto);
        Resultado resultado = salvarCommand.executar(cliente);

        cliente = (Cliente) resultado.getEntidade();
        mv.addObject("cliente", cliente);

        return mv;
    }

    @GetMapping("buscar/{id}")
    public void buscarById(@PathVariable Integer id){

        Cliente cliente = new Cliente();
        cliente.setId(id);
        Resultado resultado = buscarByIdCommand.executar(cliente);
    }
}

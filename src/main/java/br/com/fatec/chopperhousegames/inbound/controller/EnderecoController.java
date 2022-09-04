package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.core.domain.entity.TIPO_ENDERECO;
import br.com.fatec.chopperhousegames.inbound.facade.ClienteFacade;
import br.com.fatec.chopperhousegames.inbound.facade.EnderecoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("cliente/perfil")
public class EnderecoController {

    private final EnderecoFacade enderecoFacade;
    private final ClienteFacade clienteFacade;

    private static final String CLIENTE = "cliente";

    private static final String MENSAGEM = "mensagem";

    @Autowired
    public EnderecoController(EnderecoFacade enderecoFacade, ClienteFacade clienteFacade) {
        this.enderecoFacade = enderecoFacade;
        this.clienteFacade = clienteFacade;
    }

    @GetMapping("{id}/enderecos")
    public ModelAndView listarEnderecos(@PathVariable("id") String clienteDtoId) {
        ModelAndView mv = new ModelAndView("/cliente/listaEnderecos");
        mv.addObject(CLIENTE, clienteFacade.atualUsuarioLogado());
        return mv;
    }

    @GetMapping("{id}/enderecos/novo")
    public ModelAndView formularioNovoEndereco(@PathVariable("id") String clienteDto, EnderecoDTO enderecoDto) {
        ModelAndView mv = new ModelAndView("cliente/endereco/form");
        mv.addObject(CLIENTE, clienteFacade.atualUsuarioLogado());
        mv.addObject("endereco", enderecoDto);
        mv.addObject("tiposEnd", TIPO_ENDERECO.values());
        return mv;
    }

    @PostMapping("{id}/enderecos/novo")
    public ModelAndView salvaEndereco(@PathVariable("id") ClienteDTO clienteDto, @Valid EnderecoDTO enderecoDto, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();

        enderecoFacade.salvar(clienteFacade.atualUsuarioLogado(), enderecoDto);
        mv.addObject(CLIENTE, clienteDto);
        mv.setViewName("/cliente/listaEnderecos");
        mv.addObject(MENSAGEM, "Endereco criado com sucesso!");

        return mv;
    }

    @PostMapping("{id}/enderecos")
    public ModelAndView excluirEndereco(@RequestParam Long id, RedirectAttributes attributes, ClienteDTO clienteDto) {

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        enderecoFacade.excluir(id);

        mv.addObject(CLIENTE, clienteFacade.atualUsuarioLogado());
        mv.addObject(MENSAGEM, "Endereco removido com sucesso!");

        return mv;
    }

    @GetMapping("{id}/enderecos/editar/{idEnd}")
    public ModelAndView formularioEditar(@PathVariable("id") ClienteDTO clienteDto, @PathVariable("idEnd") Long id) {

        ModelAndView mv = new ModelAndView("/cliente/endereco/formEditar");

        mv.addObject("endereco", enderecoFacade.buscarPorId(id));
        mv.addObject(CLIENTE, clienteFacade.atualUsuarioLogado());
        mv.addObject("tiposEnd", TIPO_ENDERECO.values());

        return mv;
    }

    @PostMapping("{id}/enderecos/editar")
    public ModelAndView editaEndereco(@Valid EnderecoDTO enderecoDto, @PathVariable String id) {

        ClienteDTO clienteDto = clienteFacade.atualUsuarioLogado();

        enderecoFacade.editar(clienteDto, enderecoDto);

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject(CLIENTE, clienteDto);

        mv.addObject(MENSAGEM, "Endereco atualizado com sucesso!");

        return mv;
    }
}
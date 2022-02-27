package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.Endereco;
import br.com.fatec.ChopperHouseGames.core.domain.TIPO_ENDERECO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDto;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDto;
import br.com.fatec.ChopperHouseGames.inbound.facade.Facade;
import br.com.fatec.ChopperHouseGames.core.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/cliente/perfil")
public class EnderecoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    private Facade facade;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}/enderecos")
    public ModelAndView listarEnderecos(@PathVariable("id") Cliente cliente) {
        ModelAndView mv = new ModelAndView("/cliente/listaEnderecos");

        facade.salvar(cliente);

        mv.addObject("cliente",cliente);
        clienteService.usuarioLogado(cliente.getId(), mv);
        return mv;
    }

    @GetMapping("/{id}/enderecos/novo")
    public ModelAndView formularioNovoEndereco(@PathVariable("id") Cliente cliente, EnderecoDto enderecoDto){
        ModelAndView mv = new ModelAndView("cliente/endereco/form");
        mv.addObject("cliente",cliente);
        mv.addObject("endereco", enderecoDto);
        mv.addObject("tiposEnd", TIPO_ENDERECO.values());
        return mv;
    }

    @PostMapping("/{id}/enderecos/novo")
    public ModelAndView salvaEndereco(@PathVariable("id") Cliente cliente, @Valid EnderecoDto enderecoDto, BindingResult result, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView();
        Endereco endereco = enderecoDto.toEndereco();

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        cliente = clienteService.atualUsuarioLogado();
        endereco.setCliente(cliente);
        cliente.getEnderecos().add(endereco);
        facade.salvar(endereco);
        mv.addObject("cliente", cliente);

        mv.setViewName("/cliente/listaEnderecos");

        mv.addObject("mensagem", "Endereco criado com sucesso!");

        return mv;
    }

    @PostMapping("/{id}/enderecos")
    public ModelAndView excluirEndereco(@RequestParam String id, RedirectAttributes attributes, ClienteDto clienteDto){

        ModelAndView mv = new ModelAndView("/cliente/perfil");

        Endereco endereco = enderecoService.buscarById(Integer.parseInt(id));

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        facade.excluir(endereco);

        Cliente cliente = clienteService.atualUsuarioLogado();

        mv.addObject("cliente", cliente);

        mv.addObject("mensagem", "Endereco removido com sucesso!");

        return mv;
    }
    @GetMapping("/{id}/enderecos/editar/{idEnd}")
    public ModelAndView formularioEditar(@PathVariable("id") Cliente cliente, @PathVariable("idEnd") Endereco idEnd, EnderecoDto enderecoDto){

        ModelAndView mv = new ModelAndView("/cliente/endereco/formEditar");

        Endereco endereco = enderecoService.buscarById(idEnd.getId());

        cliente = clienteService.atualUsuarioLogado();

        mv.addObject("cliente", cliente);
        mv.addObject("endereco", endereco);
        mv.addObject("tiposEnd", TIPO_ENDERECO.values());

        return mv;
    }

    @PostMapping("/{id}/enderecos/editar")
    public ModelAndView editaEndereco(@Valid Endereco enderecoForm, RedirectAttributes attributes){

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        Cliente cliente = clienteService.atualUsuarioLogado();

        enderecoForm.setCliente(cliente);

        Endereco endereco = (Endereco) facade.editar(enderecoForm).getEntidade();



        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", cliente);

        mv.addObject("mensagem", "Endereco atualizado com sucesso!");

        return mv;
    }
}
package br.com.fatec.ChopperHouseGames.controller;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.domain.Endereco;
import br.com.fatec.ChopperHouseGames.domain.TIPO_ENDERECO;
import br.com.fatec.ChopperHouseGames.dto.request.EnderecoDtoForm;
import br.com.fatec.ChopperHouseGames.facade.IFacade;
import br.com.fatec.ChopperHouseGames.facade.impl.Facade;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/cliente/perfil")
public class EnderecoController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    private IFacade facade;

    @GetMapping("/{id}/enderecos")
    public ModelAndView listarEnderecos(@PathVariable("id") Cliente cliente) {
        ModelAndView mv = new ModelAndView("/cliente/listaEnderecos");

        facade.salvar(cliente);

        mv.addObject("cliente",cliente);
        clienteService.usuarioLogado(cliente.getId(), mv);
        return mv;
    }

    @GetMapping("/{id}/enderecos/novo")
    public ModelAndView formularioNovoEndereco(@PathVariable("id") Cliente cliente, EnderecoDtoForm enderecoDto){
        ModelAndView mv = new ModelAndView("cliente/endereco/form");
        mv.addObject("cliente",cliente);
        mv.addObject("endereco", enderecoDto);
        mv.addObject("tiposEnd", TIPO_ENDERECO.values());
        return mv;
    }

    @PostMapping("/{id}/enderecos/novo")
    public ModelAndView salvaEndereco(@PathVariable("id") Cliente cliente, @Valid EnderecoDtoForm enderecoDtoForm, BindingResult result, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView();
        Endereco endereco = enderecoDtoForm.toEndereco();

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        cliente = clienteService.atualUsuarioLogado();
        cliente.getEnderecos().add(endereco);
        facade.salvar(endereco);

        mv.setViewName("/cliente/perfil/" + cliente.getId());

        return mv;
    }

}

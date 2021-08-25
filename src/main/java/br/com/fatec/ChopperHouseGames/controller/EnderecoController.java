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
import br.com.fatec.ChopperHouseGames.service.IEnderecoService;
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
    private IClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    private IFacade facade;

    @Autowired
    private IEnderecoService enderecoService;

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
        endereco.setCliente(cliente);
        cliente.getEnderecos().add(endereco);
        facade.salvar(endereco);
        mv.addObject("cliente", cliente);

        mv.setViewName("/cliente/listaEnderecos");

        return mv;
    }

    @PostMapping("/{id}/enderecos")
    public ModelAndView excluirEndereco(@RequestParam String id, RedirectAttributes attributes){

        ModelAndView mv = new ModelAndView("/cliente/perfil");

        Endereco endereco = enderecoService.buscarById(Integer.parseInt(id));

        facade = new Facade(clienteRepository, enderecoRepository, cartaoCreditoRepository);
        facade.excluir(endereco);

        Cliente cliente = clienteService.atualUsuarioLogado();

        mv.addObject("cliente", cliente);

        return mv;
    }
    @GetMapping("/{id}/enderecos/editar/{idEnd}")
    public ModelAndView formularioEditar(@PathVariable("id") Cliente cliente,
                                         @PathVariable("idEnd") Endereco idEnd ,@Valid EnderecoDtoForm enderecoDtoForm,
                                         BindingResult result, RedirectAttributes attributes){

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

        attributes.addFlashAttribute("mensagem", "Usuário atualizado com sucesso!");

        return mv;
    }
}

package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.core.domain.entity.BANDEIRA;
import br.com.fatec.chopperhousegames.inbound.facade.CartaoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.ClienteFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.CartaoCreditoDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@RequestMapping("cliente/perfil")
public class CartaoCreditoController {

    private final CartaoFacade cartaoFacade;
    private final ClienteFacade clienteFacade;

    @Autowired
    public CartaoCreditoController(CartaoFacade cartaoFacade, ClienteFacade clienteFacade) {
        this.cartaoFacade = cartaoFacade;
        this.clienteFacade = clienteFacade;
    }

    @GetMapping("{id}/cartoes")
    public ModelAndView listarCartoes(@PathVariable("id") String clienteDtoId) {
        ModelAndView mv = new ModelAndView("cliente/listaCartoes");
        mv.addObject("cliente", clienteFacade.atualClienteLogado());
        return mv;
    }

    @GetMapping("{id}/cartoes/novo")
    public ModelAndView formularioNovoCartao(@PathVariable("id") Long clienteDto, CartaoCreditoDTO dto) {
        ModelAndView mv = new ModelAndView("cliente/cartao/form");
        mv.addObject("cliente", clienteFacade.atualClienteLogado());
        mv.addObject("bandeiras", BANDEIRA.values());
        mv.addObject("cartaoCredito", dto);
        return mv;
    }

    @PostMapping("{id}/cartoes/novo")
    public ModelAndView salvaCartao(@PathVariable("id") Long id, @Valid CartaoCreditoDTO dto, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("cliente/perfil");
        mv.addObject("cliente", cartaoFacade.salvarCartao(dto));
        mv.addObject("mensagem", "Cartao atualizado com sucesso!");

        return mv;
    }


    @PostMapping("{id}/cartoes")
    public ModelAndView excluirCartao(@PathVariable String id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("/cliente/perfil");
        cartaoFacade.excluirCartao(Long.parseLong(id));
        mv.addObject("cliente", clienteFacade.atualClienteLogado());
        mv.addObject("mensagem", "Cartao removido com sucesso!");

        return mv;
    }

    @GetMapping("{id}/cartoes/editar/{idCard}")
    public ModelAndView formularioEditar(@PathVariable("id") Long clienteDTO, @PathVariable("idCard") Long id) {
        ModelAndView mv = new ModelAndView("/cliente/cartao/formEditar");
        mv.addObject("cliente", clienteDTO);
        mv.addObject("cartao", cartaoFacade.buscarCartaoPorId(id));
        mv.addObject("bandeiras", BANDEIRA.values());

        return mv;
    }

    @PostMapping("{id}/cartoes/editar")
    public ModelAndView editaCartao(@Valid CartaoCreditoDTO cartaoDto, RedirectAttributes attributes, ClienteDTO dto) {

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", cartaoFacade.editarCartao(cartaoDto));
        mv.addObject("mensagem", "Cartao atualizado com sucesso!");

        return mv;
    }

}

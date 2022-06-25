package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.BANDEIRA;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.inbound.facade.CartaoFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.ClienteFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.SenhaDTO;
import br.com.fatec.ChopperHouseGames.inbound.validator.ClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    private final ClienteFacade facade;

    private final ClienteValidator validator;

    @Autowired
    public ClienteController(ClienteFacade facade, ClienteValidator validator) {
        this.facade = facade;
        this.validator = validator;
    }

    @GetMapping("/novo")
    public ModelAndView novoCliente(ClienteDTO dto, ModelAndView mv) {
        if (mv == null) {
            mv = new ModelAndView();
        }
        mv.setViewName("/cliente/form");
        mv.addObject("dto", dto);

        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvarCliente(@Valid ClienteDTO dto, BindingResult result) {

        ModelAndView mv = new ModelAndView("redirect:/");

        if (validator.validaFormularioCadastro(dto, result).hasErrors()) {
            mv.addObject("resultados", result);
            return novoCliente(dto, mv);
        }

        mv.addObject("cliente", facade.salvar(dto));

        mv.addObject("mensagem", "Usuário criado com sucesso!");

        return mv;
    }

    @GetMapping("/perfil/{id}")
    public ModelAndView perfil(@PathVariable("id") Long id) {

        ModelAndView mv = new ModelAndView();
        if (facade.usuarioEstaLogado(id)) {
            ClienteDTO dto = facade.atualUsuarioLogado();
            if (validator.validaRoleUsuario(dto)) {
                mv.setViewName("/cliente/perfil");
                mv.addObject("cliente", dto);
            } else {
                mv.setViewName("/admin/dashboard");
                mv.addObject("admin", dto);
            }
        }

        return mv;
    }

    @PostMapping("editar")
    public ModelAndView editarCliente(@ModelAttribute("cliente") ClienteDTO dto, BindingResult result) {

        ModelAndView mv = new ModelAndView("/cliente/perfil");
        mv.addObject("cliente", facade.editar(dto));

        mv.addObject("mensagem", "Usuário atualizado com sucesso!");

        return mv;
    }

    @GetMapping("/perfil/{id}/senha")
    public ModelAndView editarSenha(@PathVariable("id") Long id, SenhaDTO senhaDto, ModelAndView mv) {
        if (mv == null) {
            mv = new ModelAndView();
        }
        mv.setViewName("/cliente/senha");
        mv.addObject(facade.atualUsuarioLogado());

        return mv;
    }

    @PostMapping("/perfil/{id}/senha")
    public ModelAndView editarSenha(@PathVariable("id") Long id, @Valid SenhaDTO dto, BindingResult result) {
        ModelAndView mv = new ModelAndView("/cliente/perfil");
        ClienteDTO clienteDTO = facade.atualUsuarioLogado();

        if (validator.validaAlteracaoSenha(dto, clienteDTO, result).hasErrors()) {
            mv.addObject("resultados", result);
            return editarSenha(clienteDTO.getId(), dto, mv);
        }
        mv.addObject("cliente", facade.editarSenha(clienteDTO, dto));

        mv.addObject("mensagem", "Senha atualizada com sucesso!");

        return mv;
    }

    //TODO - FLUXO DE DEVOLUÇÃO
//    @GetMapping("perfil/{id}/devolucao/{idPed}")
//    public ModelAndView acessarFormularioDevolucao(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido){
//
//        ModelAndView mv = new ModelAndView("cliente/pedido/formDevolucao");
//
//        mv.addObject("cliente", service.atualUsuarioLogado());
//        DevolucaoDto devolucaoDto = new DevolucaoDto();
//        devolucaoDto.setPedido(pedidoService.buscarPorId(pedido.getId()));
//        mv.addObject("dto", devolucaoDto);
//
//        return mv;
//    }
//
//    @PostMapping("perfil/{id}/devolucao/{idPed}")
//    public ModelAndView solicitarDevolucaoPedido(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido, @ModelAttribute DevolucaoDto dto){
//        ModelAndView mv = new ModelAndView("cliente/perfil");
//
//        Devolucao devolucao = new Devolucao();
//        devolucao.setMotivo(dto.getMotivo());
//        devolucao.setStatusDevolucao(dto.getStatusDevolucao());
//        pedido = pedidoService.buscarPorId(pedido.getId());
//
//        Integer i = 0;
//        for(Item item : pedido.getItens()){
//            item.setQuantidadeTroca(dto.getPedido().getItens().get(i).getQuantidadeTroca());
//            i++;
//        }
//        devolucao.setPedido(pedido);
//
//        devolucaoService.salvarSolicitacaoDevolucao(devolucao);
//
//        mv.addObject("cliente", service.atualUsuarioLogado());
//        mv.addObject("mensagem", "Solicitação de troca enviada com sucesso!");
//
//        return mv;
//    }
//
//    @GetMapping("perfil/{id}/pedidos/visualizar/{idPed}")
//    public ModelAndView visualizarPedido(@PathVariable("id") Cliente cliente, @PathVariable("idPed") Pedido pedido) {
//        ModelAndView mv = new ModelAndView("cliente/pedido/detalhe");
//
//        mv.addObject("cliente", cliente);
//        mv.addObject("pedido", pedidoService.buscarPorId(pedido.getId()));
//        mv.addObject("devolucaoExist", devolucaoService.buscaDevolucaoByPedidoId(pedido));
//        mv.addObject("devolucao", new Devolucao());
//
//        return mv;
//    }
}
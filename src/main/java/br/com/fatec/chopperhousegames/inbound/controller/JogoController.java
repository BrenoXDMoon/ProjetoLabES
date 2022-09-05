package br.com.fatec.chopperhousegames.inbound.controller;

import br.com.fatec.chopperhousegames.inbound.facade.*;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.JogoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class JogoController {
    private final JogoFacade jogoFacade;
    private final GeneroFacade generoFacade;

    private final IdiomaFacade idiomaFacade;
    private PlataformaFacade plataformaFacade;
    private final EditoraFacade editoraFacade;
    private final ClienteFacade clienteFacade;

    public JogoController(JogoFacade jogoFacade, GeneroFacade generoFacade, IdiomaFacade idiomaFacade, PlataformaFacade plataformaFacade, EditoraFacade editoraFacade, ClienteFacade clienteFacade) {
        this.jogoFacade = jogoFacade;
        this.generoFacade = generoFacade;
        this.idiomaFacade = idiomaFacade;
        this.plataformaFacade = plataformaFacade;
        this.editoraFacade = editoraFacade;
        this.clienteFacade = clienteFacade;
    }

    //TODO: refatorar controller de jogo para usar facade

    @GetMapping("admin/jogos")
    public ModelAndView listaJogos(){
        ModelAndView mv = new ModelAndView("admin/jogo/lista");

        List<JogoDTO> jogos = jogoFacade.listarJogos();

        mv.addObject("jogos", jogos);

        return mv;
    }

    @GetMapping("admin/jogos/novo")
    public ModelAndView formularioNovoJogo(JogoDTO jogoDto, ModelAndView mv){

        if(mv == null){
            mv = new ModelAndView();
        }
        mv.setViewName("admin/jogo/form");

        mv.addObject("generos", generoFacade.listarGeneros());
        mv.addObject("idiomas", idiomaFacade.listarIdiomas());
        mv.addObject("plataformas", plataformaFacade.listarPlataformas());
        mv.addObject("editoras", editoraFacade.listarEditora());
        return mv;
    }

    @PostMapping("admin/jogos/novo")
    public ModelAndView cadastrarNovoJogo(@Valid JogoDTO jogoDto, BindingResult result){
        ModelAndView mv = new ModelAndView("redirect:/admin/jogos");

        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return formularioNovoJogo(jogoDto, mv);
        }
        jogoFacade.salvarJogo(jogoDto);

        mv.addObject("mensagem", "Jogo salvo com sucesso!");
        return mv;
    }

    @GetMapping("admin/jogos/editar/{id}")
    public ModelAndView fomularioEditarJogo(@PathVariable("id") Long id, ModelAndView mv){
        if(mv == null){
            mv = new ModelAndView();
        }

        mv.addObject("jogo", jogoFacade.buscarById(id));
        mv.setViewName("admin/jogo/formEditar");

        return mv;
    }

    @PostMapping
    public ModelAndView editarJogo(@Valid JogoDTO jogoDto, BindingResult result){

        ModelAndView mv = new ModelAndView();

        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return formularioNovoJogo(jogoDto, mv);
        }

        jogoFacade.editar(jogoDto);
        mv.addObject("mensagem", "Produto editado com sucesso! Id do produto " + jogoDto.getId() +"");
        mv.setViewName("admin/jogo/lista");
        return mv;
    }

    @GetMapping("jogo/detalhe/{id}")
    public ModelAndView detalheJogo(@PathVariable("id") JogoDTO jogo){

        ModelAndView mv = new ModelAndView("jogo/detalhe");

        jogo = jogoFacade.buscarById(jogo.getId());

        ClienteDTO cliente = clienteFacade.atualUsuarioLogado();

        if(cliente != null){
            mv.addObject("cliente", cliente);
        }

        mv.addObject("jogo", jogo);

        return mv;
    }

//    @PostMapping("admin/jogos/desativar/{id}")
//    public ModelAndView desativarJogo(@PathVariable("id") Jogo jogo){
//
//       ModelAndView mv = new ModelAndView("redirect:/admin/jogos");
//       jogo = service.buscarById(jogo.getId());
//       service.excluir(jogo);
//
//       return mv;
//    }
//
//    @PostMapping("admin/jogos/ativar/{id}")
//    public ModelAndView ativarJogo(@PathVariable("id") Jogo jogo){
//
//        ModelAndView mv = new ModelAndView("redirect:/admin/jogos");
//        jogo = service.buscarById(jogo.getId());
//        service.ativar(jogo);
//
//        return mv;
//    }
}

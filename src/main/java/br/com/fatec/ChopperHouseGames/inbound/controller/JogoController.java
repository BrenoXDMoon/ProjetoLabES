package br.com.fatec.ChopperHouseGames.inbound.controller;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.Jogo;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.JogoDto;
import br.com.fatec.ChopperHouseGames.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IJogoService service;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private IEditoraService editoraService;
    @Autowired
    private IPlataformaService plataformaService;
    @Autowired
    private IIdiomaService idiomaService;
    @Autowired
    private IGeneroService generoService;

    @GetMapping("admin/jogos")
    public ModelAndView listaJogos(){
        ModelAndView mv = new ModelAndView("admin/jogo/lista");

        List<Jogo> jogos = service.listar();

        mv.addObject("jogos", jogos);

        return mv;
    }

    @GetMapping("admin/jogos/novo")
    public ModelAndView formularioNovoJogo(JogoDto jogoDto, ModelAndView mv){

        if(mv == null){
            mv = new ModelAndView();
        }
        mv.setViewName("admin/jogo/form");

        mv.addObject("generos", generoService.listar());
        mv.addObject("idiomas", idiomaService.listar());
        mv.addObject("plataformas", plataformaService.listar());
        mv.addObject("editoras", editoraService.listar());
        return mv;
    }

    @PostMapping("admin/jogos/novo")
    public ModelAndView cadastrarNovoJogo(@Valid JogoDto jogoDto, BindingResult result){
        ModelAndView mv = new ModelAndView("redirect:/admin/jogos");

        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return formularioNovoJogo(jogoDto, mv);
        }
        service.salvar(jogoDto.toJogo());

        mv.addObject("mensagem", "Jogo salvo com sucesso!");
        return mv;
    }

    @GetMapping("admin/jogos/editar/{id}")
    public ModelAndView fomularioEditarJogo(@PathVariable("id") Integer id, ModelAndView mv){
        if(mv == null){
            mv = new ModelAndView();
        }
        Jogo jogo = service.buscarById(id);
        mv.setViewName("admin/jogo/formEditar");
        mv.addObject("jogo", jogo);

        return mv;
    }

    @PostMapping
    public ModelAndView editarJogo(@Valid JogoDto jogoDto, BindingResult result){

        ModelAndView mv = new ModelAndView();

        if(result.hasErrors()){
            mv.addObject("resultados", result);
            return formularioNovoJogo(jogoDto, mv);
        }

        Jogo jogo = service.editar(jogoDto.toJogo());
        mv.addObject("mensagem", "Produto editado com sucesso! Id do produto " + jogo.getId() +"");
        mv.setViewName("admin/jogo/lista");
        return mv;
    }

    @GetMapping("jogo/detalhe/{id}")
    public ModelAndView detalheJogo(@PathVariable Jogo id){

        ModelAndView mv = new ModelAndView("jogo/detalhe");

        Jogo jogo = service.buscarById(id.getId());

        Cliente cliente = clienteService.atualUsuarioLogado();

        if(cliente != null){
            mv.addObject("cliente", cliente);
        }

        mv.addObject("jogo", jogo);

        return mv;
    }

    @PostMapping("admin/jogos/desativar/{id}")
    public ModelAndView desativarJogo(@PathVariable("id") Jogo jogo){

       ModelAndView mv = new ModelAndView("redirect:/admin/jogos");
       jogo = service.buscarById(jogo.getId());
       service.excluir(jogo);

       return mv;
    }

    @PostMapping("admin/jogos/ativar/{id}")
    public ModelAndView ativarJogo(@PathVariable("id") Jogo jogo){

        ModelAndView mv = new ModelAndView("redirect:/admin/jogos");
        jogo = service.buscarById(jogo.getId());
        service.ativar(jogo);

        return mv;
    }
}

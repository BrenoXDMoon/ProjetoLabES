package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.JogoService;
import br.com.fatec.chopperhousegames.inbound.facade.JogoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.JogoDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.JogoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JogoFacadeImpl implements JogoFacade {

    private final JogoMapper mapper;

    private final JogoService jogoService;

    public JogoFacadeImpl(JogoMapper jogoMapper, JogoService jogoService) {
        this.mapper = jogoMapper;
        this.jogoService = jogoService;
    }

    @Override
    public List<JogoDTO> listarJogos() {
        return jogoService.listar().stream()
                .map(mapper::toJogoDTO)
                .toList();
    }

    @Override
    public void salvarJogo(JogoDTO jogo) {
        jogoService.salvar(mapper.toJogo(jogo));
    }

    @Override
    public JogoDTO buscarById(Integer id) {
        return mapper.toJogoDTO(jogoService.buscarById(id));
    }

    @Override
    public void editar(JogoDTO jogoDto) {
        jogoService.editar(mapper.toJogo(jogoDto));
    }
}

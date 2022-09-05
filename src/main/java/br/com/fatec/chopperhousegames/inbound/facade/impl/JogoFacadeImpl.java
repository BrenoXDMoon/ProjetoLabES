package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.JogoService;
import br.com.fatec.chopperhousegames.inbound.facade.JogoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.JogoDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.JogoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JogoFacadeImpl implements JogoFacade {

    private final JogoMapper jogoMapper;

    private final JogoService jogoService;

    public JogoFacadeImpl(JogoMapper jogoMapper, JogoService jogoService) {
        this.jogoMapper = jogoMapper;
        this.jogoService = jogoService;
    }

    @Override
    public List<JogoDTO> listarJogos() {
        return jogoService.listar().stream()
                .map(jogoMapper::toJogoDTO)
                .toList();
    }

    @Override
    public void salvarJogo(JogoDTO jogo) {
        jogoService.salvar(jogoMapper.toJogo(jogo));
    }
}

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
        return jogoService.listarJogo().stream()
                .map(mapper::toJogoDTO)
                .toList();
    }

    @Override
    public void salvarJogo(JogoDTO jogo) {
        jogoService.salvarJogo(mapper.toJogo(jogo));
    }

    @Override
    public JogoDTO buscarJogoPorId(Long id) {
        return jogoService.buscarJogoPorId(id).map(mapper::toJogoDTO).orElse(null);
    }

    @Override
    public void editarJogo(JogoDTO jogoDto) {
        jogoService.editarJogo(mapper.toJogo(jogoDto));
    }

    @Override
    public void excluirJogo(JogoDTO jogo) {
        jogoService.excluirJogo(mapper.toJogo(jogo));
    }

    @Override
    public void ativarJogo(JogoDTO jogo) {
        jogoService.ativarJogo(mapper.toJogo(jogo));
    }

    @Override
    public List<JogoDTO> listarJogosAtivos() {
        return jogoService.listarJogosAtivos().stream()
                .map(mapper::toJogoDTO)
                .toList();
    }
}

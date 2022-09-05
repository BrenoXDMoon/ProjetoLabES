package br.com.fatec.chopperhousegames.inbound.facade;

import br.com.fatec.chopperhousegames.inbound.facade.dto.JogoDTO;

import java.util.List;

public interface JogoFacade {
    List<JogoDTO> listarJogos();

    void salvarJogo(JogoDTO toJogo);

    JogoDTO buscarById(Long id);

    void editar(JogoDTO jogoDto);
}

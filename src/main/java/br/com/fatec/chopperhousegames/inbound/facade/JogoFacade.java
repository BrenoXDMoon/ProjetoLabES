package br.com.fatec.chopperhousegames.inbound.facade;

import br.com.fatec.chopperhousegames.inbound.facade.dto.JogoDTO;

import java.util.List;

public interface JogoFacade {
    List<JogoDTO> listarJogos();

    void salvarJogo(JogoDTO toJogo);

    JogoDTO buscarJogoPorId(Long id);

    void editarJogo(JogoDTO jogoDto);

    void excluirJogo(JogoDTO jogo);

    void ativarJogo(JogoDTO jogo);

    List<JogoDTO> listarJogosAtivos();
}

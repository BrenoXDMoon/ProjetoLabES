package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;

import java.util.List;
import java.util.Optional;

public interface JogoService {

    Jogo salvarJogo(Jogo jogo);
    Jogo editarJogo(Jogo jogo);
    Jogo excluirJogo(Jogo jogo);
    Jogo ativarJogo(Jogo jogo);
    List<Jogo> listarJogo();
    List<Jogo> listarJogosAtivos();
    Optional<Jogo> buscarJogoPorId(Long id);
}

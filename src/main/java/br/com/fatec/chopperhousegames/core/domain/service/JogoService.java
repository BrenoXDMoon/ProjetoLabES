package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;

import java.util.List;
import java.util.Optional;

public interface JogoService {

    void salvarJogo(Jogo jogo);
    void editarJogo(Jogo jogo);
    void excluirJogo(Jogo jogo);
    void ativarJogo(Jogo jogo);
    List<Jogo> listarTodosJogos();
    List<Jogo> listarJogosAtivos();
    Optional<Jogo> buscarJogoPorId(Long id);
}

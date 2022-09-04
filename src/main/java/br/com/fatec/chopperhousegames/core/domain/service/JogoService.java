package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;

import java.util.List;

public interface JogoService {

    Jogo salvar(Jogo jogo);
    Jogo editar(Jogo jogo);
    Jogo excluir(Jogo jogo);
    Jogo ativar(Jogo jogo);
    List<Jogo> listar();
    List<Jogo> listarAtivos();
    Jogo buscarById(Integer id);
}

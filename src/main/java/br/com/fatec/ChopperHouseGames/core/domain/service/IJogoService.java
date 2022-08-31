package br.com.fatec.ChopperHouseGames.core.domain.service;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Jogo;

import java.util.List;

public interface IJogoService {

    Jogo salvar(Jogo jogo);
    Jogo editar(Jogo jogo);
    Jogo excluir(Jogo jogo);
    Jogo ativar(Jogo jogo);
    List<Jogo> listar();
    List<Jogo> listarAtivos();
    Jogo buscarById(Integer id);
}

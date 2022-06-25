package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.Status;

import java.util.List;

public interface StatusService {

    List<Status> listarTodos();

    Status buscarById(Integer id);

    Status buscarByNome(String nome);
}
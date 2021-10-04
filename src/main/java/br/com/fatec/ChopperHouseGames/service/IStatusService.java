package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.Status;

import java.util.List;

public interface IStatusService {

    List<Status> listarTodos();

    Status buscarById(Integer id);

    Status buscarByNome(String nome);
}

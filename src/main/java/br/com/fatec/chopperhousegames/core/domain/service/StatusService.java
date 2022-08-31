package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Status;

import java.util.List;

public interface StatusService {

    List<Status> listarTodos();

    Status buscarById(Integer id);

    Status buscarByNome(String nome);
}

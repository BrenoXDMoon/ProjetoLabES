package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Status;

//TODO: refatorar para transformar em um enum
public interface StatusService {

    Status buscarById(Integer id);

    Status buscarStatusPorNome(String emProcessamento);
}

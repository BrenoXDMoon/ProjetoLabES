package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.TipoCliente;

public interface TipoClienteService {
    TipoCliente buscarById(Integer id);
    TipoCliente salvar(TipoCliente tipoCliente);
}

package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.TipoCliente;

public interface TipoClienteService {
    public TipoCliente buscarById(Integer id);
    public TipoCliente salvar(TipoCliente tipoCliente);
}

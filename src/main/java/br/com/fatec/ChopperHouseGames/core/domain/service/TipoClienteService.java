package br.com.fatec.ChopperHouseGames.core.domain.service;

import br.com.fatec.ChopperHouseGames.core.domain.entity.TipoCliente;

public interface TipoClienteService {
    public TipoCliente buscarById(Integer id);
    public TipoCliente salvar(TipoCliente tipoCliente);
}

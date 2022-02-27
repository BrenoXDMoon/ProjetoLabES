package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.TipoCliente;

import java.util.List;

public interface TipoClienteService {
    public TipoCliente buscarById(Integer id);
    public TipoCliente salvar(TipoCliente tipoCliente);
}

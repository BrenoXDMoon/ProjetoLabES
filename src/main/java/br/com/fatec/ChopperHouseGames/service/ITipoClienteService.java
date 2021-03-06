package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.TipoCliente;

import java.util.List;

public interface ITipoClienteService {
    public List<TipoCliente> buscarTodos();
    public TipoCliente buscarById(Integer id);
    public TipoCliente salvar(TipoCliente tipoCliente);
}

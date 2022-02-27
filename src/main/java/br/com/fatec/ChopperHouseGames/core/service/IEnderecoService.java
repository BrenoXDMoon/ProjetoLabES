package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.Endereco;

public interface IEnderecoService {

    Endereco buscarById(Integer id);
    Endereco salvar(Endereco end);
}

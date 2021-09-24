package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.Endereco;

public interface IEnderecoService {

    Endereco buscarById(Integer id);
    Endereco salvar(Endereco end);
}

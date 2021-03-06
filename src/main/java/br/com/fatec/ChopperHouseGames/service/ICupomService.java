package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.Cupom;

import java.util.List;

public interface ICupomService {

    List<Cupom> listarTodos();

    List<Cupom> listarCupomDesconto();

    List<Cupom> listarCupomTroca();

    Cupom buscarCupomZerado();
}

package br.com.fatec.ChopperHouseGames.core.domain.service;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Cupom;

import java.util.List;

public interface CupomService {

    List<Cupom> listarTodos();

    List<Cupom> listarCupomDesconto();

    List<Cupom> listarCupomTroca();

    Cupom buscarCupomZerado();
}

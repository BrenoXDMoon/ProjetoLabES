package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Cupom;

import java.util.List;

public interface CupomService {

    List<Cupom> listarTodos();

    List<Cupom> listarCupomDesconto();

    List<Cupom> listarCupomTroca();

    Cupom buscarCupomZerado();
}

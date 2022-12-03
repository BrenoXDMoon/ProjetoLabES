package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Cupom;

import java.util.List;
import java.util.Optional;

public interface CupomService {

    List<Cupom> listarTodosCupons();

    Optional<Cupom> buscarCupomPorId(Long id);
}

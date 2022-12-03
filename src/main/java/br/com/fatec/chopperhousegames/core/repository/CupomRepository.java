package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.Cupom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CupomRepository {

    List<Cupom> findAll();

    List<Cupom> buscarTodosPorNomeEQuantidadeMaiorQue(String nome, Integer quantidade);
    Cupom buscarCuporPorNome(String nome);

    Optional<Cupom> findById(Long id);

    Cupom save(Cupom cupom);

}

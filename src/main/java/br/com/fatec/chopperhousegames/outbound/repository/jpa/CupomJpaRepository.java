package br.com.fatec.chopperhousegames.outbound.repository.jpa;

import br.com.fatec.chopperhousegames.core.domain.entity.Cupom;
import br.com.fatec.chopperhousegames.core.domain.entity.TipoCupom;
import br.com.fatec.chopperhousegames.core.repository.CupomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CupomJpaRepository extends CupomRepository, JpaRepository<Cupom, Long> {

    @Query("SELECT c FROM Cupom c WHERE c.tipoCupom = ?1 AND c.quantidade > 0")
    List<Cupom> buscarTodosPorNomeEQuantidadeMaiorQue(TipoCupom tipoCupom, Integer quantidade);

    @Query("SELECT c FROM Cupom c WHERE c.tipoCupom = :tipoCupom AND c.quantidade > 0")
    Cupom buscarCuporPorNome(TipoCupom tipoCupom);

    @Override
    List<Cupom> findAll();

    @Override
    Optional<Cupom> findById(Long id);

    @Override
    Cupom save(Cupom cupom);
}

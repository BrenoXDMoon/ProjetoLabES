package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//TODO: refatorar para manter o padrão DDD
public interface CupomRepository extends JpaRepository<Cupom, Long> {
    List<Cupom> findAllByTipoCupom_NomeAndQuantidadeIsGreaterThan(String nome, Integer quantidade);
    Cupom findAllByTipoCupom_Nome(String nome);
}

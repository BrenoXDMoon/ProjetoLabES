package br.com.fatec.ChopperHouseGames.core.repository;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    List<Cupom> findAllByTipoCupom_NomeAndQuantidadeIsGreaterThan(String nome, Integer quantidade);
    Cupom findAllByTipoCupom_Nome(String nome);
}

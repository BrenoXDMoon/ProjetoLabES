package br.com.fatec.ChopperHouseGames.core.repository;

import br.com.fatec.ChopperHouseGames.core.domain.entity.TipoCupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCupomRepository extends JpaRepository<TipoCupom, Integer> {

    TipoCupom findByNome(String nome);
}

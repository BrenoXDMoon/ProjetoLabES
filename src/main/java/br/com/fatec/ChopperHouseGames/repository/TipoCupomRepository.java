package br.com.fatec.ChopperHouseGames.repository;

import br.com.fatec.ChopperHouseGames.domain.TipoCupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCupomRepository extends JpaRepository<TipoCupom, Integer> {

    TipoCupom findByNome(String nome);
}

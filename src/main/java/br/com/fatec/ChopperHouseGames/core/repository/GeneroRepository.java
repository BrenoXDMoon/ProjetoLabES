package br.com.fatec.ChopperHouseGames.core.repository;

import br.com.fatec.ChopperHouseGames.core.domain.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}

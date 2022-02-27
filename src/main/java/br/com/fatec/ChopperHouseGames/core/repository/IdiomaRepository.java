package br.com.fatec.ChopperHouseGames.core.repository;

import br.com.fatec.ChopperHouseGames.core.domain.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Integer> {
}

package br.com.fatec.ChopperHouseGames.core.repository;

import br.com.fatec.ChopperHouseGames.core.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByStatus(String nome);
}

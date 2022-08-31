package br.com.fatec.ChopperHouseGames.outbound.repository;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Long> {

}

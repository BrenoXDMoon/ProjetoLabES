package br.com.fatec.ChopperHouseGames.outbound.repository.jpa;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Cliente;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends JpaRepository<Cliente, Long>, ClienteRepository {

    Optional<Cliente> findByEmail(String email);

}

package br.com.fatec.ChopperHouseGames.repository;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
}

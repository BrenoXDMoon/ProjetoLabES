package br.com.fatec.ChopperHouseGames.repository;

import br.com.fatec.ChopperHouseGames.domain.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, Integer> {
}

package br.com.fatec.ChopperHouseGames.core.repository;

import br.com.fatec.ChopperHouseGames.core.domain.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {
}

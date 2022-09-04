package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
}

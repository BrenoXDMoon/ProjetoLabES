package br.com.fatec.chopperhousegames.outbound.repository.jpa;

import br.com.fatec.chopperhousegames.core.domain.entity.CartaoCredito;
import br.com.fatec.chopperhousegames.core.repository.CartaoCreditoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoCreditoJpaRepository extends CartaoCreditoRepository, JpaRepository<CartaoCredito, Long> {

}

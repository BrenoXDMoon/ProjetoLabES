package br.com.fatec.chopperhousegames.outbound.repository.jpa;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import br.com.fatec.chopperhousegames.core.repository.JogoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoJpaRepository extends JpaRepository<Jogo, Long>, JogoRepository {

}

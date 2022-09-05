package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

    Optional<Jogo> findById(Long id);
    List<Jogo> findAllByAtivoTrue();
}

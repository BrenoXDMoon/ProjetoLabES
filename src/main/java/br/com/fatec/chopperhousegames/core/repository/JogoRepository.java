package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    Optional<Jogo> findById(Integer id);
    List<Jogo> findAllByAtivoTrue();
}

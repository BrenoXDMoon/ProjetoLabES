package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByStatus(String nome);
}

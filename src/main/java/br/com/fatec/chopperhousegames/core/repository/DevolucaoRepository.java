package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.Devolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {

    Optional<Devolucao> findByPedido_Id(Long id);

}

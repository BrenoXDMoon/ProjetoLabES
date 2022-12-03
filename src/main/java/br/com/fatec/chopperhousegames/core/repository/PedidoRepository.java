package br.com.fatec.chopperhousegames.core.repository;

import br.com.fatec.chopperhousegames.core.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
//TODO: refatorar para manter o padrão DDD
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findAllByStatus_Status(String status);

    List<Pedido> buscarPedidosEntre(LocalDate dataInicial, LocalDate dataFinal);
}

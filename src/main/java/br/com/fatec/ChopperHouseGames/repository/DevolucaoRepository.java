package br.com.fatec.ChopperHouseGames.repository;

import br.com.fatec.ChopperHouseGames.domain.Devolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, Integer> {

    Devolucao findByPedido_Id(Integer id);

}

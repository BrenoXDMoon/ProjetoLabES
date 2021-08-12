package br.com.fatec.ChopperHouseGames.repository;

import br.com.fatec.ChopperHouseGames.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}

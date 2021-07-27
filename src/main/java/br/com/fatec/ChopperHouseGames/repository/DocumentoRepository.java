package br.com.fatec.ChopperHouseGames.repository;

import br.com.fatec.ChopperHouseGames.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
}

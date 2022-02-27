package br.com.fatec.ChopperHouseGames.inbound.facade;

import br.com.fatec.ChopperHouseGames.core.domain.EntidadeDominio;
import br.com.fatec.ChopperHouseGames.core.domain.Resultado;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EntidadeDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ResultadoDTO;

import java.util.Optional;

public interface Facade {
    ResultadoDTO salvar(EntidadeDTO ent);
    ResultadoDTO editar(EntidadeDTO ent);
    ResultadoDTO excluir(EntidadeDTO ent);
    ResultadoDTO listar(EntidadeDTO ent);
    Optional<EntidadeDTO> buscarPorId(Integer id);
}

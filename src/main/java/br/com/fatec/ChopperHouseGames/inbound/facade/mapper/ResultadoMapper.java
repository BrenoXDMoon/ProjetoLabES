package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Resultado;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ResultadoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResultadoMapper {

    Resultado toResultado(ResultadoDTO dto);

    ResultadoDTO toResultadoDTO(Resultado dto);
}

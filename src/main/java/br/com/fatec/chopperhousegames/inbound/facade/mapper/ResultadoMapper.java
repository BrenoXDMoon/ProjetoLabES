package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.Resultado;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ResultadoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResultadoMapper {

    Resultado toResultado(ResultadoDTO dto);

    ResultadoDTO toResultadoDTO(Resultado dto);
}

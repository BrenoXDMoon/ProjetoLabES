package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.Idioma;
import br.com.fatec.chopperhousegames.inbound.facade.dto.IdiomaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdiomaMapper {

    IdiomaDTO toIdiomaDTO(Idioma idiomaDTO);

}

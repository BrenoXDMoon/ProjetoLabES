package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.Genero;
import br.com.fatec.chopperhousegames.inbound.facade.dto.GeneroDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroDTO toGeneroDTO(Genero generoDTO);

}

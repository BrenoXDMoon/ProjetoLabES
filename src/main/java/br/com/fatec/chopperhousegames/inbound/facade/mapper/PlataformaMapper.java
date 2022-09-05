package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.Plataforma;
import br.com.fatec.chopperhousegames.inbound.facade.dto.PlataformaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlataformaMapper {

    PlataformaDTO toDTO(Plataforma plataformaDTO);

}

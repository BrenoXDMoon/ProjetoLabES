package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.Editora;
import br.com.fatec.chopperhousegames.inbound.facade.dto.EditoraDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditoraMapper {

    EditoraDTO toDTO(Editora editora);

}

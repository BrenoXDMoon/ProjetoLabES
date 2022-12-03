package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import br.com.fatec.chopperhousegames.inbound.facade.dto.JogoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JogoMapper {

    JogoDTO toJogoDTO(Jogo jogo);

    Jogo toJogo(JogoDTO jogo);
}
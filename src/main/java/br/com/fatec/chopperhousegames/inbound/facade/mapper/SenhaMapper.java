package br.com.fatec.chopperhousegames.inbound.facade.mapper;

import br.com.fatec.chopperhousegames.core.domain.entity.Senha;
import br.com.fatec.chopperhousegames.inbound.facade.dto.SenhaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SenhaMapper {

    Senha toSenha(SenhaDTO dto);

}

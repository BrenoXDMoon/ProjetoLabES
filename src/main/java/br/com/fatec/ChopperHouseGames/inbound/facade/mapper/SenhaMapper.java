package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.Senha;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.SenhaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SenhaMapper {

    Senha toSenha(SenhaDTO dto);

}

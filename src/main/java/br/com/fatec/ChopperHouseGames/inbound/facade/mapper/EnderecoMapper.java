package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.Endereco;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(source = "cidade", target = "cidade.cidade")
    @Mapping(source = "estado", target = "cidade.estado.estado")
    Endereco toDomain(EnderecoDTO dto);

    @Mapping(source = "cidade.cidade", target = "cidade")
    @Mapping(source = "cidade.estado.estado", target = "estado")
    EnderecoDTO toDTO(Endereco domain);


}

package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.Endereco;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)//TODO: implementar a atribuição da data de criação e cliente
    @Mapping(target = "cliente", ignore = true)
    @Mapping(source = "cidade", target = "cidade.cidade")
    @Mapping(source = "estado", target = "cidade.estado.estado")
    Endereco toDomain(EnderecoDTO dto);

    @Mapping(source = "cidade.cidade", target = "cidade")
    @Mapping(source = "cidade.estado.estado", target = "estado")
    EnderecoDTO toDTO(Endereco domain);

    List<EnderecoDTO> toListDto(List<Endereco> buscarTodosPorCliente);
}

package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClienteDTO dto);

    ClienteDTO toClienteDTO(Cliente cliente);
}

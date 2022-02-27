package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClienteDTO dto);

    ClienteDTO toClienteDTO(Cliente cliente);

    List<ClienteDTO> toListDTO(List<Cliente> listar);
}

package br.com.fatec.ChopperHouseGames.inbound.facade.mapper;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CartaoCreditoMapper.class, EnderecoMapper.class})
public interface ClienteMapper {

    @Mapping(source = "senha", target = "senha.senha")
    @Mapping(source = "confirmaSenha", target = "senha.confirmaSenha")
    Cliente toCliente(ClienteDTO dto);

    @Mapping(source = "senha.senha", target = "senha")
    @Mapping(source = "senha.confirmaSenha", target = "confirmaSenha")
    ClienteDTO toClienteDTO(Cliente cliente);

    List<ClienteDTO> toListDTO(List<Cliente> listar);
}

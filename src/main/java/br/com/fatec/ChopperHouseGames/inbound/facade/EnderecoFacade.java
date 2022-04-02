package br.com.fatec.ChopperHouseGames.inbound.facade;

import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDTO;

import java.util.List;

public interface EnderecoFacade {
    
    
    EnderecoDTO salvar(ClienteDTO cliente, EnderecoDTO enderecoDto);

    List<EnderecoDTO> listarEnderecosCliente(ClienteDTO cliente);

    void excluir(Long id);

    EnderecoDTO buscarPorId(Long id);

    Object editar(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoForm);
}

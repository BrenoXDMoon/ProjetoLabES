package br.com.fatec.ChopperHouseGames.inbound.facade;

import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDTO;

public interface EnderecoFacade {
    
    
    EnderecoDTO salvar(ClienteDTO cliente, EnderecoDTO enderecoDto);

    void excluir(Long id);

    EnderecoDTO buscarPorId(Long id);

    EnderecoDTO editar(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoForm);
}

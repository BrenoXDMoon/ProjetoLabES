package br.com.fatec.chopperhousegames.inbound.facade;

import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.EnderecoDTO;

public interface EnderecoFacade {
    
    
    EnderecoDTO salvar(ClienteDTO cliente, EnderecoDTO enderecoDto);

    void excluir(Long id);

    EnderecoDTO buscarPorId(Long id);

    EnderecoDTO editar(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoForm);
}

package br.com.fatec.chopperhousegames.inbound.facade;

import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.EnderecoDTO;

public interface EnderecoFacade {
    
    
    void salvarEndereco(ClienteDTO cliente, EnderecoDTO enderecoDto);

    void excluirEndereco(Long id);

    EnderecoDTO buscarEnderecoPorId(Long id);

    void editarEndereco(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoForm);
}

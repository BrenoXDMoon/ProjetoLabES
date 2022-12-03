package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Endereco;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;

public interface EnderecoService {

    Endereco buscarEnderecoPorId(Long id);
    Endereco salvarEndereco(Endereco end);

    void excluirEndereco(Long id);

    Endereco editarEndereco(ClienteDTO atualUsuarioLogado, Endereco enderecoForm);
}

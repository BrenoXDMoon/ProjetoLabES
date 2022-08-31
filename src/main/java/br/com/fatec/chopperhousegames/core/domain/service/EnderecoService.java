package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Endereco;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;

public interface EnderecoService {

    Endereco buscarPorId(Long id);
    Endereco salvar(Endereco end);

    void excluir(Long id);

    Endereco editar(ClienteDTO atualUsuarioLogado, Endereco enderecoForm);
}

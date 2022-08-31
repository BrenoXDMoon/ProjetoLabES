package br.com.fatec.ChopperHouseGames.core.domain.service;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Endereco;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;

public interface EnderecoService {

    Endereco buscarPorId(Long id);
    Endereco salvar(Endereco end);

    void excluir(Long id);

    Endereco editar(ClienteDTO atualUsuarioLogado, Endereco enderecoForm);
}

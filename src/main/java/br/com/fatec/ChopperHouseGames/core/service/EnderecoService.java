package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.Endereco;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDTO;

import java.util.List;

public interface EnderecoService {

    Endereco buscarPorId(Long id);
    Endereco salvar(Endereco end);

    void excluir(Long id);

    Endereco editar(ClienteDTO atualUsuarioLogado, Endereco enderecoForm);
}

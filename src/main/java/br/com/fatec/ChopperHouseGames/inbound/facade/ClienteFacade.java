package br.com.fatec.ChopperHouseGames.inbound.facade;


import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.SenhaDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteFacade extends Facade {

    ClienteDTO salvar(ClienteDTO ent);

    ClienteDTO editar(ClienteDTO ent);

    ClienteDTO excluir(ClienteDTO ent);

    ClienteDTO ativaInativa(Integer id);

    List<ClienteDTO> listar();

    Optional<ClienteDTO> buscarPorId(Integer id);

    Optional<ClienteDTO> buscarPorEmail(String email);

    ClienteDTO atualUsuarioLogado();

    Boolean usuarioEstaLogado(Integer id);

    ClienteDTO editarSenha(ClienteDTO clienteDTO, SenhaDTO dto);
}

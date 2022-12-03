package br.com.fatec.chopperhousegames.inbound.facade;


import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.SenhaDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteFacade {

    ClienteDTO salvarCliente(ClienteDTO ent);

    ClienteDTO editarCliente(ClienteDTO ent);

    ClienteDTO excluirCliente(ClienteDTO ent);

    void ativaInativaCliente(Long id);

    List<ClienteDTO> listarTodosOsClientes();

    ClienteDTO buscarClientePorId(Long id);

    Optional<ClienteDTO> buscarClientePorEmail(String email);

    ClienteDTO atualClienteLogado();

    Boolean clienteEstaLogado(Long id);

    ClienteDTO editarSenhaCliente(ClienteDTO clienteDTO, SenhaDTO dto);
}

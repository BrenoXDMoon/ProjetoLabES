package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Cliente;
import br.com.fatec.chopperhousegames.core.domain.entity.Senha;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvarCliente(Cliente cliente);

    Cliente editarCliente(Cliente cliente);

    Cliente excluirCliente(Cliente cliente);

    List<Cliente> listarTodosOsClientes();

    Cliente buscarClientePorId(Long id);

    Optional<Cliente> buscarClientePorEmail(String email);

    Cliente atualClienteLogado();

    boolean clienteEstaLogado(Long id);

    Cliente editarSenhaCliente(Cliente cliente, Senha senha);

    Cliente ativaInativaCliente(Long id);
}

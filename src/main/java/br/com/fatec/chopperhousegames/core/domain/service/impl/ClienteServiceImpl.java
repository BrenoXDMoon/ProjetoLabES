package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Cliente;
import br.com.fatec.chopperhousegames.core.domain.entity.Senha;
import br.com.fatec.chopperhousegames.core.domain.entity.TipoCliente;
import br.com.fatec.chopperhousegames.core.domain.service.ClienteService;
import br.com.fatec.chopperhousegames.core.repository.ClienteRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {

        cliente.setTipoCliente(TipoCliente.BASICO);

        cliente.setRoles("CLIENTE");

        return repository.saveAndFlush(cliente);
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        return repository.saveAndFlush(cliente);
    }

    @Override
    public Cliente excluirCliente(Cliente cliente) {
        cliente.setAtivo(false);
        return editarCliente(cliente);
    }

    @Override
    public List<Cliente> listarTodosOsClientes() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> buscarClientePorEmail(String email) {
        return repository.findByEmail(email);

    }

    @Override
    public Cliente atualClienteLogado() {

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails user) {
            return this.buscarClientePorEmail(user.getUsername()).orElse(new Cliente());
        }
        return null;
    }

    @Override
    public boolean clienteEstaLogado(Long id) {
        return this.atualClienteLogado().getId().equals(id);
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return repository.findById(id).orElse(new Cliente());
    }

    @Override
    public Cliente editarSenhaCliente(Cliente cliente, Senha senha) {
        cliente.setSenha(senha);
        return editarCliente(cliente);
    }

    @Override
    public Cliente ativaInativaCliente(Long id) {

        Cliente cliente = buscarClientePorId(id);
        if (cliente.getId() != null) {
            cliente.setAtivo(!cliente.isAtivo());
        }

        return editarCliente(cliente);
    }
}

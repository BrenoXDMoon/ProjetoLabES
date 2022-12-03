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
    public Cliente salvar(Cliente cliente) {

        cliente.setTipoCliente(TipoCliente.BASICO);

        cliente.setRoles("CLIENTE");

        return repository.saveAndFlush(cliente);
    }

    @Override
    public Cliente editar(Cliente cliente) {
        return repository.saveAndFlush(cliente);
    }

    @Override
    public Cliente excluir(Cliente cliente) {
        cliente.setAtivo(false);
        return editar(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorEmail(String email) {
        return repository.findByEmail(email);

    }

    @Override
    public Cliente atualUsuarioLogado() {

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails user) {
            return this.buscarPorEmail(user.getUsername()).orElse(new Cliente());
        }
        return null;
    }

    @Override
    public boolean usuarioEstaLogado(Long id) {
        return this.atualUsuarioLogado().getId().equals(id);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(new Cliente());
    }

    @Override
    public Cliente editarSenha(Cliente cliente, Senha senha) {
        cliente.setSenha(senha);
        return editar(cliente);
    }

    @Override
    public Cliente ativaInativa(Long id) {

        Cliente cliente = buscarPorId(id);
        if (cliente.getId() != null) {
            cliente.setAtivo(!cliente.isAtivo());
        }

        return editar(cliente);
    }
}

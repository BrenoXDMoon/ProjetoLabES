package br.com.fatec.ChopperHouseGames.core.domain.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.entity.Senha;
import br.com.fatec.ChopperHouseGames.core.domain.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.domain.service.TipoClienteService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    ClienteRepository repository;

    TipoClienteService tipoClienteService;

    public ClienteServiceImpl(ClienteRepository repository, TipoClienteService tipoClienteService) {
        this.repository = repository;
        this.tipoClienteService = tipoClienteService;
    }

    @Override
    public Cliente salvar(Cliente cliente) {

        cliente.setTipoCliente(tipoClienteService.buscarById(1));

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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;

        if (principal != null) {
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
                return this.buscarPorEmail(email).orElse(new Cliente());
            }
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

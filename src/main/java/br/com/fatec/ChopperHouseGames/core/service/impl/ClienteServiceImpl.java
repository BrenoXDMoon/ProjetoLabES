package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.Senha;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.service.TipoClienteService;

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

        if(principal != null){
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
                Cliente cliente = this.buscarPorEmail(email).orElse(null);
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean usuarioEstaLogado(Integer id) {
        return this.atualUsuarioLogado().getId().equals(id);
    }

    @Override
    public Optional<Cliente> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Cliente editarSenha(Cliente cliente, Senha senha) {
        cliente.setSenha(senha);
        return editar(cliente);
    }

    @Override
    public Cliente ativaInativa(Integer id) {

        Cliente cliente = buscarPorId(id).orElse(new Cliente());

        if(cliente.isAtivo()){
            cliente.setAtivo(false);
        }else{
            cliente.setAtivo(true);
        }

        return editar(cliente);
    }
}

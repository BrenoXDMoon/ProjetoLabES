package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.service.TipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
    public Cliente buscarByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public Cliente atualUsuarioLogado() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;

        if(principal != null){
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
                Cliente cliente = this.buscarByEmail(email);
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
    public Optional<Cliente> buscarById(Integer id) {
        return repository.findById(id);
    }
}

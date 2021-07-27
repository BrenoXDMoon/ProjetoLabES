package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository repository;

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Cliente buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return repository.saveAndFlush(cliente);
    }

    @Override
    public Cliente buscarByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public Cliente atualUsuarioLogado() {
        return null;
    }

    @Override
    public void usuarioLogado(Integer id, ModelAndView mv) {

    }

    @Override
    public boolean usuarioIsLogado(Integer id) {
        return false;
    }
}
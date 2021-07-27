package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.TipoCliente;
import br.com.fatec.ChopperHouseGames.repository.TipoClienteRepository;
import br.com.fatec.ChopperHouseGames.service.ITipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoClienteServiceImpl implements ITipoClienteService {

    @Autowired
    TipoClienteRepository repository;


    @Override
    public List<TipoCliente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public TipoCliente buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public TipoCliente salvar(TipoCliente tipoCliente) {
        return repository.saveAndFlush(tipoCliente);
    }
}

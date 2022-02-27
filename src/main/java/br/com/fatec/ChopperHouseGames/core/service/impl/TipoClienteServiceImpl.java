package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.TipoCliente;
import br.com.fatec.ChopperHouseGames.core.repository.TipoClienteRepository;
import br.com.fatec.ChopperHouseGames.core.service.TipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoClienteServiceImpl implements TipoClienteService {

    @Autowired
    TipoClienteRepository repository;

    @Override
    public TipoCliente buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public TipoCliente salvar(TipoCliente tipoCliente) {
        return repository.saveAndFlush(tipoCliente);
    }
}

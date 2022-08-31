package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.TipoCliente;
import br.com.fatec.chopperhousegames.core.repository.TipoClienteRepository;
import br.com.fatec.chopperhousegames.core.domain.service.TipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

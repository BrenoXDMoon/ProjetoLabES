package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Plataforma;
import br.com.fatec.chopperhousegames.core.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService implements br.com.fatec.chopperhousegames.core.domain.service.PlataformaService {

    @Autowired
    PlataformaRepository repository;

    @Override
    public List<Plataforma> listar() {
        return repository.findAll();
    }
}

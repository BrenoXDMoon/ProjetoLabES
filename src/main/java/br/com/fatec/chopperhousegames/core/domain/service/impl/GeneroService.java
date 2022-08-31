package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Genero;
import br.com.fatec.chopperhousegames.core.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService implements br.com.fatec.chopperhousegames.core.domain.service.GeneroService {

    @Autowired
    GeneroRepository repository;

    @Override
    public List<Genero> listar() {
        return repository.findAll();
    }
}

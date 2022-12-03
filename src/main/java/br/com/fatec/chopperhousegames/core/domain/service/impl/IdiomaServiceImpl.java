package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Idioma;
import br.com.fatec.chopperhousegames.core.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaServiceImpl implements br.com.fatec.chopperhousegames.core.domain.service.IdiomaService {

    @Autowired
    IdiomaRepository repository;

    @Override
    public List<Idioma> listarIdiomas() {
        return repository.findAll();
    }
}

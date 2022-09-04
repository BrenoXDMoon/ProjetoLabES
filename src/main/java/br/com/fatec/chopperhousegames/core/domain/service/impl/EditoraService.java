package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.repository.EditoraRepository;
import br.com.fatec.chopperhousegames.core.domain.entity.Editora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService implements br.com.fatec.chopperhousegames.core.domain.service.EditoraService {

    @Autowired
    EditoraRepository repository;

    @Override
    public List<Editora> listar() {
        return repository.findAll();
    }
}

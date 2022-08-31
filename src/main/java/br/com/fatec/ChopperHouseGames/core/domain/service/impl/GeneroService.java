package br.com.fatec.ChopperHouseGames.core.domain.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Genero;
import br.com.fatec.ChopperHouseGames.core.repository.GeneroRepository;
import br.com.fatec.ChopperHouseGames.core.domain.service.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService implements IGeneroService {

    @Autowired
    GeneroRepository repository;

    @Override
    public List<Genero> listar() {
        return repository.findAll();
    }
}

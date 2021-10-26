package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Genero;
import br.com.fatec.ChopperHouseGames.repository.GeneroRepository;
import br.com.fatec.ChopperHouseGames.service.IGeneroService;
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

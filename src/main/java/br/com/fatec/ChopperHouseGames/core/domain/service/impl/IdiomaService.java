package br.com.fatec.ChopperHouseGames.core.domain.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Idioma;
import br.com.fatec.ChopperHouseGames.core.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService implements br.com.fatec.ChopperHouseGames.core.domain.service.IdiomaService {

    @Autowired
    IdiomaRepository repository;

    @Override
    public List<Idioma> listar() {
        return repository.findAll();
    }
}

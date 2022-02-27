package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Idioma;
import br.com.fatec.ChopperHouseGames.core.repository.IdiomaRepository;
import br.com.fatec.ChopperHouseGames.core.service.IIdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService implements IIdiomaService {

    @Autowired
    IdiomaRepository repository;

    @Override
    public List<Idioma> listar() {
        return repository.findAll();
    }
}

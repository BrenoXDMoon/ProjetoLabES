package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Idioma;
import br.com.fatec.ChopperHouseGames.repository.IdiomaRepository;
import br.com.fatec.ChopperHouseGames.service.IIdiomaService;
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

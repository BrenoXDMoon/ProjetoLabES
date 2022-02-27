package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Plataforma;
import br.com.fatec.ChopperHouseGames.core.repository.PlataformaRepository;
import br.com.fatec.ChopperHouseGames.core.service.IPlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService implements IPlataformaService {

    @Autowired
    PlataformaRepository repository;

    @Override
    public List<Plataforma> listar() {
        return repository.findAll();
    }
}
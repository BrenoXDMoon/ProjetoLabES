package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Status;
import br.com.fatec.ChopperHouseGames.repository.StatusRepository;
import br.com.fatec.ChopperHouseGames.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements IStatusService {

    @Autowired
    StatusRepository repository;

    @Override
    public List<Status> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Status buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Status buscarByNome(String nome) {
        return repository.findByStatus(nome);
    }
}

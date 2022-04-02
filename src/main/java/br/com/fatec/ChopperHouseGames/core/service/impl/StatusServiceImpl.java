package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Status;
import br.com.fatec.ChopperHouseGames.core.repository.StatusRepository;
import br.com.fatec.ChopperHouseGames.core.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository repository;

    @Override
    public List<Status> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Status buscarById(Integer id) {
        //TODO: Retornar exceção caso não encontre o status
        return repository.findById(id).get();
    }

    @Override
    public Status buscarByNome(String nome) {
        return repository.findByStatus(nome);
    }
}

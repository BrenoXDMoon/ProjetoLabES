package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Status;
import br.com.fatec.chopperhousegames.core.domain.service.StatusService;
import br.com.fatec.chopperhousegames.core.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//TODO: Refatorar Status para ser um Enum
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository repository;

    @Override
    public Status buscarById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    @Override
    public Status buscarStatusPorNome(String nome) {
        return repository.findByStatus(nome);
    }
}

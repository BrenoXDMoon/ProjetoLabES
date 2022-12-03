package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Cupom;
import br.com.fatec.chopperhousegames.core.repository.CupomRepository;
import br.com.fatec.chopperhousegames.core.domain.service.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CupomServiceImpl implements CupomService {

    @Autowired
    private CupomRepository repository;

    @Override
    public List<Cupom> listarTodosCupons() {
        return repository.findAll();
    }

    @Override
    public Optional<Cupom> buscarCupomPorId(Long id) {
        return repository.findById(id);
    }
}
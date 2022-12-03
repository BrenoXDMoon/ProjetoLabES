package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.TipoCupom;
import br.com.fatec.chopperhousegames.core.domain.service.TipoCupomService;
import br.com.fatec.chopperhousegames.core.repository.TipoCupomRepository;
import org.springframework.stereotype.Service;

@Service
//TODO: Refatorar TipoCupom para ser um Enum
public class TipoCupomServiceImpl implements TipoCupomService {

    TipoCupomRepository repository;

    @Override
    public TipoCupom buscarPorNome(String troca) {
        return repository.findByNome(troca);
    }
}

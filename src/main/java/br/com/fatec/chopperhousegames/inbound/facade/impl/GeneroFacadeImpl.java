package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.GeneroService;
import br.com.fatec.chopperhousegames.inbound.facade.GeneroFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.GeneroDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.GeneroMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeneroFacadeImpl implements GeneroFacade {

    private final GeneroService service;

    private final GeneroMapper mapper;

    public GeneroFacadeImpl(GeneroService service, GeneroMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<GeneroDTO> listarGeneros() {
        return service.listar().stream().map(mapper::toGeneroDTO).toList();
    }
}

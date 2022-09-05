package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.IdiomaService;
import br.com.fatec.chopperhousegames.inbound.facade.IdiomaFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.IdiomaDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.IdiomaMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdiomaFacadeImpl implements IdiomaFacade {


    private final IdiomaService service;

    private final IdiomaMapper mapper;

    public IdiomaFacadeImpl(IdiomaService service, IdiomaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<IdiomaDTO> listarIdiomas() {
        return service.listar().stream().map(mapper::toIdiomaDTO).toList();
    }
}

package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.PlataformaService;
import br.com.fatec.chopperhousegames.inbound.facade.PlataformaFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.PlataformaDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.PlataformaMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlataformaFacadeImpl implements PlataformaFacade {

    private final PlataformaService service;

    private final PlataformaMapper mapper;

    public PlataformaFacadeImpl(PlataformaService service, PlataformaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<PlataformaDTO> listarPlataformas() {
        return service.listar().stream().map(mapper::toDTO).toList();
    }
}

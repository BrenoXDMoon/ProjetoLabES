package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.EditoraService;
import br.com.fatec.chopperhousegames.inbound.facade.EditoraFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.EditoraDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.EditoraMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EditoraFacadeImpl implements EditoraFacade {
    private final EditoraService service;

    private final EditoraMapper mapper;

    public EditoraFacadeImpl(EditoraService service, EditoraMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<EditoraDTO> listarEditora() {
        return service.listarEditoras().stream().map(mapper::toDTO).toList();
    }
}

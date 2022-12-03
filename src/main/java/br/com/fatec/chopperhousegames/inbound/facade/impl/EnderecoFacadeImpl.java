package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.EnderecoService;
import br.com.fatec.chopperhousegames.inbound.facade.EnderecoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.EnderecoDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.EnderecoMapper;
import org.springframework.stereotype.Component;

@Component
public class EnderecoFacadeImpl implements EnderecoFacade {
    private final EnderecoService service;

    private final EnderecoMapper mapper;


    public EnderecoFacadeImpl(EnderecoService service, EnderecoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public void salvarEndereco(ClienteDTO cliente, EnderecoDTO enderecoDto) {
        mapper.toDTO(service.salvarEndereco(mapper.toDomain(enderecoDto)));
    }

    @Override
    public void excluirEndereco(Long id) {
        service.excluirEndereco(id);
    }

    @Override
    public EnderecoDTO buscarEnderecoPorId(Long id) {
        return mapper.toDTO(service.buscarEnderecoPorId(id));
    }

    @Override
    public void editarEndereco(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoDto) {
        mapper.toDTO(service.editarEndereco(atualUsuarioLogado, mapper.toDomain(enderecoDto)));
    }
}

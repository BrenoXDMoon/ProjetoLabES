package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.EnderecoService;
import br.com.fatec.chopperhousegames.inbound.facade.EnderecoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.EnderecoDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.EnderecoMapper;
import org.springframework.stereotype.Component;

@Component
public class EnderecoFacadeImpl implements EnderecoFacade {
    private final EnderecoService enderecoService;

    private final EnderecoMapper enderecoMapper;


    public EnderecoFacadeImpl(EnderecoService service, EnderecoService enderecoService, EnderecoMapper enderecoMapper) {
        this.enderecoService = enderecoService;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public EnderecoDTO salvar(ClienteDTO cliente, EnderecoDTO enderecoDto) {
        return enderecoMapper.toDTO(enderecoService.salvar(enderecoMapper.toDomain(enderecoDto)));
    }

    @Override
    public void excluir(Long id) {
        enderecoService.excluir(id);
    }

    @Override
    public EnderecoDTO buscarPorId(Long id) {
        return enderecoMapper.toDTO(enderecoService.buscarPorId(id));
    }

    @Override
    public EnderecoDTO editar(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoDto) {
        return enderecoMapper.toDTO(enderecoService.editar(atualUsuarioLogado, enderecoMapper.toDomain(enderecoDto)));
    }
}

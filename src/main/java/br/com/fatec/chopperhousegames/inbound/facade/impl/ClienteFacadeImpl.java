package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.ClienteService;
import br.com.fatec.chopperhousegames.inbound.facade.ClienteFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.SenhaDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.ClienteMapper;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.SenhaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteFacadeImpl implements ClienteFacade {

    private final ClienteMapper mapper;
    private final ClienteService service;
    private final SenhaMapper senhaMapper;

    @Autowired
    public ClienteFacadeImpl(ClienteMapper mapper, ClienteService service, SenhaMapper senhaMapper) {
        this.mapper = mapper;
        this.service = service;
        this.senhaMapper = senhaMapper;
    }

    @Override
    public ClienteDTO salvarCliente(ClienteDTO dto) {
        return mapper.toClienteDTO(service.salvarCliente(mapper.toCliente(dto)));
    }

    @Override
    public ClienteDTO editarCliente(ClienteDTO dto) {
        return mapper.toClienteDTO(service.editarCliente(mapper.toCliente(dto)));
    }

    @Override
    public ClienteDTO excluirCliente(ClienteDTO dto) {
        return mapper.toClienteDTO(service.excluirCliente(mapper.toCliente(dto)));
    }

    @Override
    public void ativaInativaCliente(Long id) {

        mapper.toClienteDTO(service.ativaInativaCliente(id));
    }

    @Override
    public List<ClienteDTO> listarTodosOsClientes() {
        return mapper.toListDTO(service.listarTodosOsClientes());
    }

    @Override
    public ClienteDTO buscarClientePorId(Long id) {
        return mapper.toClienteDTO(service.buscarClientePorId(id));
    }

    @Override
    public Optional<ClienteDTO> buscarClientePorEmail(String email) {
        return Optional.of(mapper.toClienteDTO(service.buscarClientePorEmail(email).orElse(null)));
    }

    @Override
    public ClienteDTO atualClienteLogado() {
        return mapper.toClienteDTO(service.atualClienteLogado());
    }

    @Override
    public Boolean clienteEstaLogado(Long id) {
        return service.clienteEstaLogado(id);
    }

    @Override
    public ClienteDTO editarSenhaCliente(ClienteDTO clienteDTO, SenhaDTO dto) {
        return mapper.toClienteDTO(service.editarSenhaCliente(mapper.toCliente(clienteDTO), senhaMapper.toSenha(dto)));
    }

}

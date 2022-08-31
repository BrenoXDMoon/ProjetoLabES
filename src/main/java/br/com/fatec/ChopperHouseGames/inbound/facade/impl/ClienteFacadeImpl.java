package br.com.fatec.ChopperHouseGames.inbound.facade.impl;

import br.com.fatec.ChopperHouseGames.core.domain.service.ClienteService;
import br.com.fatec.ChopperHouseGames.inbound.facade.ClienteFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.SenhaDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ClienteMapper;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.SenhaMapper;
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
    public ClienteDTO salvar(ClienteDTO dto) {
        return mapper.toClienteDTO(service.salvar(mapper.toCliente(dto)));
    }

    @Override
    public ClienteDTO editar(ClienteDTO dto) {
        return mapper.toClienteDTO(service.editar(mapper.toCliente(dto)));
    }

    @Override
    public ClienteDTO excluir(ClienteDTO dto) {
        return mapper.toClienteDTO(service.excluir(mapper.toCliente(dto)));
    }

    @Override
    public void ativaInativa(Long id) {

        mapper.toClienteDTO(service.ativaInativa(id));
    }

    @Override
    public List<ClienteDTO> listar() {
        return mapper.toListDTO(service.listar());
    }

    @Override
    public ClienteDTO buscarPorId(Long id) {
        return mapper.toClienteDTO(service.buscarPorId(id));
    }

    @Override
    public Optional<ClienteDTO> buscarPorEmail(String email) {
        return Optional.of(mapper.toClienteDTO(service.buscarPorEmail(email).orElse(null)));
    }

    @Override
    public ClienteDTO atualUsuarioLogado() {
        return mapper.toClienteDTO(service.atualUsuarioLogado());
    }

    @Override
    public Boolean usuarioEstaLogado(Long id) {
        return service.usuarioEstaLogado(id);
    }

    @Override
    public ClienteDTO editarSenha(ClienteDTO clienteDTO, SenhaDTO dto) {
        return mapper.toClienteDTO(service.editarSenha(mapper.toCliente(clienteDTO), senhaMapper.toSenha(dto)));
    }

}

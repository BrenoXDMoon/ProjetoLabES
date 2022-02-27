package br.com.fatec.ChopperHouseGames.inbound.facade.impl;


import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.inbound.facade.ClienteFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.SenhaDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ClienteMapper;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ResultadoMapper;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.SenhaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteFacadeImpl implements ClienteFacade {

    private final ClienteMapper mapper;
    private final ResultadoMapper resultadoMapper;
    private final ClienteService service;
    private SenhaMapper senhaMapper;

    @Autowired
    public ClienteFacadeImpl(ClienteMapper mapper, ResultadoMapper resultadoMapper, ClienteService service) {
        this.mapper = mapper;
        this.resultadoMapper = resultadoMapper;
        this.service = service;
    }

    @Override
    public ClienteDTO salvar(ClienteDTO dto) {
        return null;
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
    public ClienteDTO ativaInativa(Integer id) {

        return mapper.toClienteDTO(service.ativaInativa(id));
    }

    @Override
    public List<ClienteDTO> listar() {
        return mapper.toListDTO(service.listar());
    }

    @Override
    public Optional<ClienteDTO> buscarPorId(Integer id) {
        return Optional.of(mapper.toClienteDTO(service.buscarPorId(id).get()));
    }

    @Override
    public Optional<ClienteDTO> buscarPorEmail(String email) {
        return Optional.of(mapper.toClienteDTO(service.buscarPorEmail(email)));
    }

    @Override
    public ClienteDTO atualUsuarioLogado() {
        return mapper.toClienteDTO(service.atualUsuarioLogado());
    }

    @Override
    public Boolean usuarioEstaLogado(Integer id) {
        return service.usuarioEstaLogado(id);
    }

    @Override
    public ClienteDTO editarSenha(ClienteDTO clienteDTO, SenhaDTO dto) {
        return mapper.toClienteDTO(service.editarSenha(mapper.toCliente(clienteDTO), senhaMapper.toSenha(dto)));
    }

}

package br.com.fatec.ChopperHouseGames.inbound.facade.impl;


import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.inbound.facade.ClienteFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EntidadeDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ResultadoDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ClienteMapper;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ResultadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteFacadeImpl implements ClienteFacade {

    private final ClienteMapper mapper;
    private final ResultadoMapper resultadoMapper;
    private final ClienteService service;

    @Autowired
    public ClienteFacadeImpl(ClienteMapper mapper, ResultadoMapper resultadoMapper, ClienteService service) {
        this.mapper = mapper;
        this.resultadoMapper = resultadoMapper;
        this.service = service;
    }

    @Override
    public ResultadoDTO salvar(EntidadeDTO ent) {
        return null;
    }

    @Override
    public ResultadoDTO editar(EntidadeDTO ent) {
        return null;
    }

    @Override
    public ResultadoDTO excluir(EntidadeDTO ent) {
        return null;
    }

    @Override
    public ResultadoDTO listar(EntidadeDTO ent) {
        return null;
    }

    @Override
    public Optional<EntidadeDTO> buscarPorId(Integer id) {
        return Optional.of(mapper.toClienteDTO(service.buscarById(id).get()));
    }

    @Override
    public Optional<ClienteDTO> buscarPorEmail(String email) {
        return Optional.of(mapper.toClienteDTO(service.buscarByEmail(email)));
    }

    @Override
    public ClienteDTO atualUsuarioLogado() {
        return mapper.toClienteDTO(service.atualUsuarioLogado());
    }

    @Override
    public Boolean usuarioEstaLogado(Integer id) {
        return service.usuarioEstaLogado(id);
    }

}

package br.com.fatec.ChopperHouseGames.inbound.facade.impl;

import br.com.fatec.ChopperHouseGames.core.service.EnderecoService;
import br.com.fatec.ChopperHouseGames.inbound.facade.EnderecoFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.EnderecoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoFacadeImpl implements EnderecoFacade {

    //TODO: IMPORTAR SERVICE DE ENDERECO

    EnderecoMapper enderecoMapper;
    EnderecoService service;

    public EnderecoFacadeImpl(EnderecoMapper enderecoMapper, EnderecoService service) {
        this.enderecoMapper = enderecoMapper;
        this.service = service;
    }

    @Override
    public EnderecoDTO salvar(ClienteDTO cliente, EnderecoDTO enderecoDto) {
        return null;
    }

    @Override
    public List<EnderecoDTO> listarEnderecosCliente(ClienteDTO cliente) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }

    @Override
    public EnderecoDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public Object editar(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoForm) {
        return null;
    }
}

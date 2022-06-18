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
    private final EnderecoFacade enderecoFacade;

    public EnderecoFacadeImpl(EnderecoService service, EnderecoFacade enderecoFacade) {
        this.enderecoFacade = enderecoFacade;
    }

    @Override
    public EnderecoDTO salvar(ClienteDTO cliente, EnderecoDTO enderecoDto) {
        return enderecoFacade.salvar(cliente, enderecoDto);
    }

    @Override
    public List<EnderecoDTO> listarEnderecosCliente(ClienteDTO cliente) {
        return enderecoFacade.listarEnderecosCliente(cliente);
    }

    @Override
    public void excluir(Long id) {
        enderecoFacade.excluir(id);
    }

    @Override
    public EnderecoDTO buscarPorId(Long id) {
        return enderecoFacade.buscarPorId(id);
    }

    @Override
    public Object editar(ClienteDTO atualUsuarioLogado, EnderecoDTO enderecoForm) {
        return enderecoFacade.editar(atualUsuarioLogado, enderecoForm);
    }
}

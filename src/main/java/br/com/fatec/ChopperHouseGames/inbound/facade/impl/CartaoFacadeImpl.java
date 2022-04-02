package br.com.fatec.ChopperHouseGames.inbound.facade.impl;

import br.com.fatec.ChopperHouseGames.core.service.CartaoService;
import br.com.fatec.ChopperHouseGames.inbound.facade.CartaoFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.CartaoCreditoMapper;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ClienteMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoFacadeImpl implements CartaoFacade {

    private CartaoService service;
    private CartaoCreditoMapper mapper;
    private ClienteMapper clienteMapper;

    public CartaoFacadeImpl(CartaoService service, CartaoCreditoMapper mapper, ClienteMapper clienteMapper) {
        this.service = service;
        this.mapper = mapper;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public List<CartaoCreditoDTO> listar() {
        return mapper.toCartaoCreditoDTOList(service.listar());
    }

    @Override
    public ClienteDTO salvar(ClienteDTO clienteDto, CartaoCreditoDTO dto) {
        return clienteMapper.toClienteDTO(service.salvar(clienteMapper.toCliente(clienteDto), mapper.toCartao(dto)));
    }

    @Override
    public ClienteDTO excluir(Integer id) {
        return clienteMapper.toClienteDTO(service.excluir(id));
    }

    @Override
    public CartaoCreditoDTO buscarPorId(Integer id) {
        return mapper.toCartaoDTO(service.buscarPorId(id));
    }

    @Override
    public ClienteDTO editar(CartaoCreditoDTO cartaoDto) {
        return clienteMapper.toClienteDTO(service.editar(mapper.toCartao(cartaoDto)));
    }
}

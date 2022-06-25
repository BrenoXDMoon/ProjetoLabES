package br.com.fatec.ChopperHouseGames.inbound.facade.impl;

import br.com.fatec.ChopperHouseGames.core.service.CartaoService;
import br.com.fatec.ChopperHouseGames.inbound.facade.CartaoFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.CartaoCreditoMapper;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ClienteMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoFacadeImpl implements CartaoFacade {

    private final CartaoService service;
    private final CartaoCreditoMapper mapper;

    public CartaoFacadeImpl(CartaoService service, CartaoCreditoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<CartaoCreditoDTO> listar() {
        return mapper.toCartaoCreditoDTOList(service.listar());
    }

    @Override
    public CartaoCreditoDTO salvar(CartaoCreditoDTO dto) {
        return mapper.toCartaoDTO(service.salvar(mapper.toCartao(dto)));
    }

    @Override
    public void excluir(Long id) {
        service.excluir(id);
    }

    @Override
    public CartaoCreditoDTO buscarPorId(Long id) {
        return mapper.toCartaoDTO(service.buscarPorId(id));
    }

    @Override
    public CartaoCreditoDTO editar(CartaoCreditoDTO cartaoDto) {
        return mapper.toCartaoDTO(service.editar(mapper.toCartao(cartaoDto)));
    }
}
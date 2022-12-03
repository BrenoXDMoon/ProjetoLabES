package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.CartaoService;
import br.com.fatec.chopperhousegames.inbound.facade.CartaoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.CartaoCreditoDTO;
import br.com.fatec.chopperhousegames.inbound.facade.mapper.CartaoCreditoMapper;
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
    public List<CartaoCreditoDTO> listarTodosOsCartoes() {
        return mapper.toCartaoCreditoDTOList(service.listarTodosOsCartoes());
    }

    @Override
    public CartaoCreditoDTO salvarCartao(CartaoCreditoDTO dto) {
        return mapper.toCartaoDTO(service.salvarCartao(mapper.toCartao(dto)));
    }

    @Override
    public void excluirCartao(Long id) {
        service.excluirCartao(id);
    }

    @Override
    public CartaoCreditoDTO buscarCartaoPorId(Long id) {
        return mapper.toCartaoDTO(service.buscarCartaoPorId(id));
    }

    @Override
    public CartaoCreditoDTO editarCartao(CartaoCreditoDTO cartaoDto) {
        return mapper.toCartaoDTO(service.salvarCartao(mapper.toCartao(cartaoDto)));
    }
}
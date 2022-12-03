package br.com.fatec.chopperhousegames.inbound.facade;

import br.com.fatec.chopperhousegames.inbound.facade.dto.CartaoCreditoDTO;

import java.util.List;

public interface CartaoFacade {

    CartaoCreditoDTO buscarCartaoPorId(Long id);

    List<CartaoCreditoDTO> listarTodosOsCartoes();

    CartaoCreditoDTO salvarCartao(CartaoCreditoDTO dto);

    void excluirCartao(Long id);

    CartaoCreditoDTO editarCartao(CartaoCreditoDTO cartaoDto);
}

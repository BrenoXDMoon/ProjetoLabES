package br.com.fatec.ChopperHouseGames.inbound.facade;

import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDTO;

import java.util.List;

public interface CartaoFacade {
    List<CartaoCreditoDTO> listar();

    CartaoCreditoDTO salvar(CartaoCreditoDTO dto);

    void excluir(Long id);

    CartaoCreditoDTO buscarPorId(Long id);

    CartaoCreditoDTO editar(CartaoCreditoDTO cartaoDto);
}

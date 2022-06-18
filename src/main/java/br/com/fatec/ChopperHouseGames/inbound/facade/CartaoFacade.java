package br.com.fatec.ChopperHouseGames.inbound.facade;

import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;

import java.util.List;

public interface CartaoFacade {
    List<CartaoCreditoDTO> listar();

    ClienteDTO salvar(ClienteDTO clienteDto, CartaoCreditoDTO dto);

    ClienteDTO excluir(Long id);

    CartaoCreditoDTO buscarPorId(Long id);

    ClienteDTO editar(CartaoCreditoDTO cartaoDto);
}

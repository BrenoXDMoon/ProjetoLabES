package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.CartaoCredito;
import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.CartaoCreditoDTO;

import java.util.List;

public interface CartaoService {
    CartaoCredito buscarPorId(Long id);

    List<CartaoCredito> listar();

    Cliente salvar(Cliente cliente, CartaoCredito cartao);

    Cliente excluir(Long id);

    Cliente editar(CartaoCredito cartao);
}

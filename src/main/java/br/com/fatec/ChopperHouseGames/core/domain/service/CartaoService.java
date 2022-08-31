package br.com.fatec.ChopperHouseGames.core.domain.service;

import br.com.fatec.ChopperHouseGames.core.domain.entity.CartaoCredito;

import java.util.List;

public interface CartaoService {
    CartaoCredito buscarPorId(Long id);

    List<CartaoCredito> listar();

    CartaoCredito salvar(CartaoCredito cartao);

    void excluir(Long id);

    CartaoCredito editar(CartaoCredito cartao);
}

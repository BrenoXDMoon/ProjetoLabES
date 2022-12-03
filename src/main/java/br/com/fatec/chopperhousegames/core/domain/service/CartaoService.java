package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.CartaoCredito;

import java.util.List;

public interface CartaoService {
    CartaoCredito buscarCartaoPorId(Long id);

    List<CartaoCredito> listarTodosOsCartoes();

    CartaoCredito salvarCartao(CartaoCredito cartao);

    void excluirCartao(Long id);
}

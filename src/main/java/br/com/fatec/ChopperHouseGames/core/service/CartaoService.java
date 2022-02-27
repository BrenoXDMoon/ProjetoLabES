package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.CartaoCredito;

public interface CartaoService {
    CartaoCredito buscarById(Integer id);
    CartaoCredito salvar(CartaoCredito cartaoCredito);
}

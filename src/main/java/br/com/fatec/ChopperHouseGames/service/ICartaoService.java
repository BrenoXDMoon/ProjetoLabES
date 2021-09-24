package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.CartaoCredito;

public interface ICartaoService {
    CartaoCredito buscarById(Integer id);
    CartaoCredito salvar(CartaoCredito cartaoCredito);
}

package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;

public interface ICarrinhoService {
     void adicionarItemCarrinho(Cliente cliente, Integer idJogo, Integer quantidade);
    void removerItemCarrinho(Cliente cliente, Integer itemId);
}

package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.Cliente;

public interface ICarrinhoService {
     void adicionarItemCarrinho(Cliente cliente, Integer idJogo, Integer quantidade);
    void removerItemCarrinho(Cliente cliente, Integer itemId);
}

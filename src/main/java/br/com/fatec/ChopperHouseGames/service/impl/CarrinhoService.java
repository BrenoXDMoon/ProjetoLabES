package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.service.ICarrinhoService;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService implements ICarrinhoService {

    @Override
    public void adicionarItemCarrinho(Cliente cliente, Integer idJogo, Integer quantidade) {

    }

    @Override
    public void removerItemCarrinho(Cliente cliente, Integer idJogo) {

    }
}

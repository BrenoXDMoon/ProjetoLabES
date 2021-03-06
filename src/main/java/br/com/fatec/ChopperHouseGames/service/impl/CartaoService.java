package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.CartaoCredito;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.service.ICartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService implements ICartaoService {

    @Autowired
    CartaoCreditoRepository repository;

    @Override
    public CartaoCredito buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public CartaoCredito salvar(CartaoCredito cartaoCredito) {
        return repository.saveAndFlush(cartaoCredito);
    }
}

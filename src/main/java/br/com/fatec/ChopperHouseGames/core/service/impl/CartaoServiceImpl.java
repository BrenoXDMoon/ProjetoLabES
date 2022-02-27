package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.CartaoCredito;
import br.com.fatec.ChopperHouseGames.core.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.core.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoServiceImpl implements CartaoService {

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
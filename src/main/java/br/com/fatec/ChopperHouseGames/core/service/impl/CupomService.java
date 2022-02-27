package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.service.ICupomService;
import br.com.fatec.ChopperHouseGames.core.domain.Cupom;
import br.com.fatec.ChopperHouseGames.core.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CupomService implements ICupomService {

    @Autowired
    private CupomRepository repository;

    @Override
    public List<Cupom> listarTodos() {
        return repository.findAll();
    }

    @Override
    public List<Cupom> listarCupomDesconto() {
        return repository.findAllByTipoCupom_NomeAndQuantidadeIsGreaterThan("DESCONTO", 0);
    }

    @Override
    public List<Cupom> listarCupomTroca() {
        return repository.findAllByTipoCupom_NomeAndQuantidadeIsGreaterThan("TROCA", 0);
    }

    @Override
    public Cupom buscarCupomZerado() {
        return repository.findAllByTipoCupom_Nome("ZERADO");
    }
}
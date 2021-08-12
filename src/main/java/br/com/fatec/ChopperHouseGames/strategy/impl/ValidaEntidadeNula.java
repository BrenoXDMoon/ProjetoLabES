package br.com.fatec.ChopperHouseGames.strategy.impl;

import br.com.fatec.ChopperHouseGames.domain.EntidadeDominio;
import br.com.fatec.ChopperHouseGames.strategy.IStrategy;

public class ValidaEntidadeNula implements IStrategy {
    @Override
    public String processar(EntidadeDominio ent) {
        if(ent == null){
            return "Entidade Nula";
        }
        return null;
    }
}

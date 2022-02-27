package br.com.fatec.ChopperHouseGames.inbound.strategy.impl;

import br.com.fatec.ChopperHouseGames.core.domain.EntidadeDominio;
import br.com.fatec.ChopperHouseGames.inbound.strategy.IStrategy;

public class ValidaEntidadeNula implements IStrategy {
    @Override
    public String processar(EntidadeDominio ent) {
        if(ent == null){
            return "Entidade Nula";
        }
        return null;
    }
}

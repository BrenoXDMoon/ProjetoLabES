package br.com.fatec.chopperhousegames.inbound.strategy.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.EntidadeDominio;
import br.com.fatec.chopperhousegames.inbound.strategy.IStrategy;

public class ValidaEntidadeNula implements IStrategy {
    @Override
    public String processar(EntidadeDominio ent) {
        if(ent == null){
            return "Entidade Nula";
        }
        return null;
    }
}

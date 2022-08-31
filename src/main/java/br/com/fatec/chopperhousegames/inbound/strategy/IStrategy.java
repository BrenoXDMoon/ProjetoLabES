package br.com.fatec.chopperhousegames.inbound.strategy;

import br.com.fatec.chopperhousegames.core.domain.entity.EntidadeDominio;

public interface IStrategy {

    String processar(EntidadeDominio ent);
}

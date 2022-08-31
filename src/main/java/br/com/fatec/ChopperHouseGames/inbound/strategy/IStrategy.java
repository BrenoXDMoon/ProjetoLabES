package br.com.fatec.ChopperHouseGames.inbound.strategy;

import br.com.fatec.ChopperHouseGames.core.domain.entity.EntidadeDominio;

public interface IStrategy {

    String processar(EntidadeDominio ent);
}

package br.com.fatec.ChopperHouse.strategy;

import br.com.fatec.ChopperHouse.model.EntidadeDominio;

public interface IStrategy {

    boolean processar(EntidadeDominio ent);
}

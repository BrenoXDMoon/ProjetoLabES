package br.com.fatec.ChopperHouse.command;

import br.com.fatec.ChopperHouse.model.EntidadeDominio;
import br.com.fatec.ChopperHouse.model.Resultado;

public interface ICommand {

    Resultado executar(EntidadeDominio ent);
}

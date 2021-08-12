package br.com.fatec.ChopperHouseGames.facade;

import br.com.fatec.ChopperHouseGames.domain.EntidadeDominio;
import br.com.fatec.ChopperHouseGames.domain.Resultado;

public interface IFacade {
    Resultado salvar(EntidadeDominio ent);
    Resultado editar(EntidadeDominio ent);
    Resultado excluir(EntidadeDominio ent);
    Resultado listar(EntidadeDominio ent);
}

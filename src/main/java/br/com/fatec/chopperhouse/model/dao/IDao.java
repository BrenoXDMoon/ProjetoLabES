package br.com.fatec.chopperhouse.model.dao;

import br.com.fatec.chopperhouse.model.domain.EntidadeDominio;
import br.com.fatec.chopperhouse.model.domain.Resultado;

public interface IDao {

    Resultado salvar(EntidadeDominio ent);
    Resultado listar(EntidadeDominio ent);
    Resultado buscarById(EntidadeDominio ent);
    Resultado editar(EntidadeDominio ent);
    Resultado excluir(EntidadeDominio ent);
    Resultado ativar(EntidadeDominio ent);

}

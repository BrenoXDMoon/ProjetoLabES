package br.com.fatec.ChopperHouse.fachada;

import br.com.fatec.ChopperHouse.model.EntidadeDominio;
import br.com.fatec.ChopperHouse.model.Resultado;

public interface IFachada {
    Resultado salvar(EntidadeDominio ent);
    Resultado listar(EntidadeDominio ent);
    Resultado buscarById(EntidadeDominio ent);
    Resultado editar(EntidadeDominio ent);
    Resultado excluir(EntidadeDominio ent);
    Resultado ativar(EntidadeDominio ent);
}

package br.com.fatec.chopperhouse.fachada;

import br.com.fatec.chopperhouse.models.EntidadeDominio;
import br.com.fatec.chopperhouse.models.Resultado;

public interface IFachada {
    Resultado salvar(EntidadeDominio ent);
    Resultado listar(EntidadeDominio ent);
    Resultado buscarById(EntidadeDominio ent);
    Resultado editar(EntidadeDominio ent);
    Resultado excluir(EntidadeDominio ent);
    Resultado ativar(EntidadeDominio ent);
}

package br.com.fatec.chopperhousegames.inbound.facade.dto;

import br.com.fatec.chopperhousegames.core.domain.entity.EntidadeDominio;

import java.util.ArrayList;
import java.util.List;

public class ResultadoDTO extends EntidadeDTO {

    private String mensagem;
    private List<EntidadeDominio> entidades;
    private EntidadeDominio entidade;

    public ResultadoDTO(){
        entidades = new ArrayList<>();
    }

    public void add(EntidadeDominio ent) {
        entidades.add(ent);
    }

}

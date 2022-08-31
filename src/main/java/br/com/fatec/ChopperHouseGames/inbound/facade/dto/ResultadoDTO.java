package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import br.com.fatec.ChopperHouseGames.core.domain.entity.EntidadeDominio;

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

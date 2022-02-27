package br.com.fatec.ChopperHouseGames.core.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Resultado {

    private String mensagem;
    private List<EntidadeDominio> entidades;
    private EntidadeDominio entidade;

    public Resultado(){
        entidades = new ArrayList<>();
    }

    public void add(EntidadeDominio ent) {
        entidades.add(ent);
    }
}
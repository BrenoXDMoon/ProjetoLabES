package br.com.fatec.chopperhouse.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Resultado {

    private EntidadeDominio entidade;
    private List<EntidadeDominio> entidades;
    private String mensagem;

    public void add(EntidadeDominio ent) {
        if(entidades == null) {
            entidades = new ArrayList<EntidadeDominio>();
        }
        entidades.add(ent);
    }
}

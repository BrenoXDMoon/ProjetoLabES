package br.com.fatec.ChopperHouse.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Resultado {

    private EntidadeDominio entidade;
    private List<EntidadeDominio> entidades;
    private String mensagem;
}

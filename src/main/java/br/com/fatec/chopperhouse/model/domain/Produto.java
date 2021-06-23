package br.com.fatec.chopperhouse.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Produto extends EntidadeDominio{

    private String nome;
    private double valor;
    private String descricao;
}
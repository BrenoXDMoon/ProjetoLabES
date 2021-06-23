package br.com.fatec.chopperhouse.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cidade extends EntidadeDominio{
    private String cidade;
    private Estado estado = new Estado();
}

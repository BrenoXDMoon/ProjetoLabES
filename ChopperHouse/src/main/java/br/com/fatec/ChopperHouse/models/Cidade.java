package br.com.fatec.chopperhouse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cidade extends EntidadeDominio{

    private String cidade;
    @Embedded
    private Estado estado;
}

package br.com.fatec.chopperhouse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Senha extends EntidadeDominio{

    @Column(name = "password")
    private String senha;

    @Transient
    private String confirmaSenha;

    @Transient
    private String senhaTemp;
}

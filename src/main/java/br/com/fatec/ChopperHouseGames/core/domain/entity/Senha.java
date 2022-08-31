package br.com.fatec.ChopperHouseGames.core.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Data
public class Senha {

    private String senha;
    @Transient
    private String confirmaSenha;
}

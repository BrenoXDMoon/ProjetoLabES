package br.com.fatec.ChopperHouseGames.core.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class Cidade {
    private String cidade;
    @Embedded
    private Estado estado;
}

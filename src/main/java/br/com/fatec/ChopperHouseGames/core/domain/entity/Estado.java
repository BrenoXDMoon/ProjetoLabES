package br.com.fatec.ChopperHouseGames.core.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Estado {
    private String estado;
}

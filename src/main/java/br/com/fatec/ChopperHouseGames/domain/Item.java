package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Data
public class Item extends EntidadeDominio {

    @ManyToOne
    private Jogo jogo;
    private Integer quantidade;
    private Integer quantidadeTroca;

}

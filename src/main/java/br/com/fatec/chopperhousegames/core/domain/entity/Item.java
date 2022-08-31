package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Item extends EntidadeDominio {

    @ManyToOne
    private Jogo jogo;
    private Integer quantidade;
    private Integer quantidadeTroca;

}

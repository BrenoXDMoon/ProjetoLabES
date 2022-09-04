package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Estado {
    private String estado;
}

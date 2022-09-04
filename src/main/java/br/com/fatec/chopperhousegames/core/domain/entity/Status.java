package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Status extends EntidadeDominio {
    private String status;
}

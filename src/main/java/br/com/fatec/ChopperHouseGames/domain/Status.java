package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Status extends EntidadeDominio {
    private String status;
}

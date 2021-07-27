package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dataCriacao;

    public EntidadeDominio(){
        this.dataCriacao = new Date();
    }
}

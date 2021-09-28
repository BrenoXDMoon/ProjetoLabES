package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tipo_cupom")
@Entity
@Data
public class TipoCupom extends EntidadeDominio{
    private String nome;
}
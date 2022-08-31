package br.com.fatec.ChopperHouseGames.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tipo_cupom")
@Entity
@Data
public class TipoCupom extends EntidadeDominio{
    private String nome;
}
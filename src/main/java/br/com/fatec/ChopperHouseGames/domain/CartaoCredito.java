package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class CartaoCredito extends EntidadeDominio {

    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    private BANDEIRA bandeira;
    private String nomeImpresso;
    private String validade;
    private String codigoSeguranca;

}

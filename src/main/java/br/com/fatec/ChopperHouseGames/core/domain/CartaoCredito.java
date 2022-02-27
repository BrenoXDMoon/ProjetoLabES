package br.com.fatec.ChopperHouseGames.core.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartaoCredito extends EntidadeDominio {

    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    private BANDEIRA bandeira;
    private String nomeImpresso;
    private String validade;
    private String codigoSeguranca;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}

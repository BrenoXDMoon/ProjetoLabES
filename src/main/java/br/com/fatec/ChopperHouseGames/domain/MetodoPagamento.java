package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MetodoPagamento extends EntidadeDominio{
    private Double valorPagamento;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cartao_credito_id")
    private CartaoCredito cartaoCredito;
}

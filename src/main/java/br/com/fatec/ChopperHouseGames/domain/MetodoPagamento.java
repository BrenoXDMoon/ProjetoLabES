package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class MetodoPagamento extends EntidadeDominio{
    private Double valorPagamento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartao_credito_id")
    private CartaoCredito cartaoCredito;
}

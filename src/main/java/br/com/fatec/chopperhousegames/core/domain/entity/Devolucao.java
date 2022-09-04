package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
public class Devolucao extends EntidadeDominio{
    @NotEmpty(message = "O campo Motivo é obrigatório")
    @Size(max = 254)
    private String motivo;

    private String resposta;

    @OneToOne(cascade = CascadeType.ALL)
    private Pedido pedido;

    @Enumerated(EnumType.ORDINAL)
    private StatusDevolucao statusDevolucao;
}

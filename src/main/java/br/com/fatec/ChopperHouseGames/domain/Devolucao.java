package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
    private StatusDevolucao statusDevolucao;

    public Devolucao() {
        this.statusDevolucao = StatusDevolucao.AGUARDANDO_RESPOSTA;
    }
}

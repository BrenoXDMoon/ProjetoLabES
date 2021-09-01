package br.com.fatec.ChopperHouseGames.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Devolucao extends EntidadeDominio{
    @NotNull(message = "O campo Motivo é obrigatório")
    @NotBlank(message = "O campo Motivo é obrigatório")
    @Size(max = 254)
    private String reason;

    private String answer;

    @OneToOne(cascade = CascadeType.ALL)
    private Pedido pedido;
    private StatusDevolucao statusDevolucao;

    public Devolucao() {
        this.statusDevolucao = StatusDevolucao.AGUARDANDO_RESPOSTA;
    }
}

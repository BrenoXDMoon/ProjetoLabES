package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import br.com.fatec.ChopperHouseGames.core.domain.Pedido;
import br.com.fatec.ChopperHouseGames.core.domain.StatusDevolucao;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class DevolucaoDto {
    @NotEmpty(message = "O campo Motivo é obrigatório")
    @Size(max = 254)
    private String motivo;

    private String resposta;

    @OneToOne(cascade = CascadeType.ALL)
    private Pedido pedido;

    @Enumerated(EnumType.ORDINAL)
    private StatusDevolucao statusDevolucao;
}

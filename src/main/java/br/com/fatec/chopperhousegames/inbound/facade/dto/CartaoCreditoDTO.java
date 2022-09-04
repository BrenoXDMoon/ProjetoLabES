package br.com.fatec.chopperhousegames.inbound.facade.dto;

import br.com.fatec.chopperhousegames.core.domain.entity.BANDEIRA;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class CartaoCreditoDTO extends EntidadeDTO {

    @NotBlank(message = "O Número do cartão não pode ser vazio")
    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    private BANDEIRA bandeira;

    @NotBlank(message = "O Nome Impresso do cartão não pode ser vazio")
    private String nomeImpresso;

    @NotBlank(message = "A Validade do cartão não pode ser vazia")
    private String validade;

    @NotBlank(message = "O CVV do cartão não pode ser vazio")
    private String codigoSeguranca;
}

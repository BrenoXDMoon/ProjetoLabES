package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import br.com.fatec.ChopperHouseGames.core.domain.BANDEIRA;
import br.com.fatec.ChopperHouseGames.core.domain.CartaoCredito;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class CartaoCreditoDTO extends EntidadeDTO{

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

    public CartaoCredito toCartao(){

        CartaoCredito cartao = new CartaoCredito();
        cartao.setNumeroCartao(this.numeroCartao);
        cartao.setBandeira(this.bandeira);
        cartao.setNomeImpresso(this.nomeImpresso);
        cartao.setValidade(this.validade);
        cartao.setCodigoSeguranca(this.codigoSeguranca);

        return cartao;
    }
}

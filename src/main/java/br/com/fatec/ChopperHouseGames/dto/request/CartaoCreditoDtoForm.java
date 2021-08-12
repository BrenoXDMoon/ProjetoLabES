package br.com.fatec.ChopperHouseGames.dto.request;

import br.com.fatec.ChopperHouseGames.domain.BANDEIRA;
import br.com.fatec.ChopperHouseGames.domain.CartaoCredito;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class CartaoCreditoDtoForm {

    @NotBlank(message = "O Número do cartão não pode ser vazio")
    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "A bandeira do cartão não pode ser vazia")
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

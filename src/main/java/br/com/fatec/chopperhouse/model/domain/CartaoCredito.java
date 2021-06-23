package br.com.fatec.chopperhouse.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartaoCredito extends EntidadeDominio{

    private String numeroCartao;
    private BANDEIRA bandeira;
    private String nomeImpresso;
    private String validade;
    private String codigoSeguranca;

}

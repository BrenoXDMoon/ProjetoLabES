package br.com.fatec.chopperhouse.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco extends EntidadeDominio{
    private Cidade cidade = new Cidade();
    private String logradouro;
    private String numero;
    private String cep;
    private String complemento;
    private String bairro;
    private TIPO_ENDERECO tipoEndereco;
}

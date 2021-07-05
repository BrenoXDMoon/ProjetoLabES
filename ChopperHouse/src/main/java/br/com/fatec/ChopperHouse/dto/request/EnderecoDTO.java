package br.com.fatec.chopperhouse.dto.request;

import br.com.fatec.chopperhouse.models.Cidade;
import br.com.fatec.chopperhouse.models.TipoEndereco;
import lombok.Data;

@Data
public class EnderecoDTO {

    private Integer id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cep;
    private Cidade cidade;
    private TipoEndereco tpEndereco;
}

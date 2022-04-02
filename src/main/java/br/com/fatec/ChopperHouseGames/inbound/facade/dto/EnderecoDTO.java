package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import br.com.fatec.ChopperHouseGames.core.domain.*;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EnderecoDTO extends EntidadeDTO {

    @NotEmpty(message = "Logradouro não pode estar em branco")
    private String logradouro;

    @NotEmpty(message = "Número do endereço não pode estar em branco")
    private String numero;

    @NotEmpty(message = "CEP não pode estar em branco")
    private String cep;

    private String complemento;

    @NotNull
    private TIPO_ENDERECO tipoEndereco;

    @NotEmpty(message = "Cidade não pode estar em branco")
    private String cidade;

    @NotEmpty(message = "Estado não pode estar em branco")
    private String estado;

}

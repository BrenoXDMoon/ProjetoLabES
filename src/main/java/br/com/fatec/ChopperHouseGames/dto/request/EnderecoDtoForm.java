package br.com.fatec.ChopperHouseGames.dto.request;

import br.com.fatec.ChopperHouseGames.domain.*;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EnderecoDtoForm {

    @NotBlank(message = "Logradouro não pode estar em branco")
    @NotNull
    private String logradouro;

    @NotBlank(message = "Número do endereço não pode estar em branco")
    @NotNull
    private String numero;

    @NotBlank(message = "CEP não pode estar em branco")
    @NotNull
    private String cep;

    private String complemento;

    @NotNull
    private TIPO_ENDERECO tipoEndereco;

    @NotBlank(message = "Cidade não pode estar em branco")
    @NotNull
    private String cidade;

    @NotBlank(message = "Estado não pode estar em branco")
    @NotNull
    private String estado;


    public Endereco toEndereco(){

        Endereco endereco = new Endereco();
        Cidade cidade = new Cidade();
        Estado estado = new Estado();

        endereco.setLogradouro(this.logradouro);
        endereco.setNumero(this.numero);
        endereco.setCep(this.cep);
        endereco.setTipoEndereco(this.tipoEndereco);
        if(this.complemento != null){
            endereco.setComplemento(this.complemento);
        }
        estado.setEstado(this.estado);
        cidade.setEstado(estado);
        cidade.setCidade(this.cidade);
        endereco.setCidade(cidade);

        return endereco;
    }

}

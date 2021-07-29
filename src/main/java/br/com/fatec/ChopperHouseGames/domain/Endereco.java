package br.com.fatec.ChopperHouseGames.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Endereco extends EntidadeDominio{

    @NotBlank(message = "Logradouro não pode estar em branco")
    @NotNull
    private String logradouro;

    @NotBlank(message = "Número do endereço não pode estar em branco")
    @NotNull
    private String numero;

    @NotBlank(message = "CEP não pode estar em branco")
    @NotNull
    private String CEP;

    private String complemento;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Cliente cliente;

    @NotNull
    @ManyToOne
    @JoinColumn
    private TipoEndereco tipoEndereco;

    @NotNull
    @ManyToOne
    @JoinColumn
    private Cidade cidade;
}

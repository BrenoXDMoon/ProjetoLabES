package br.com.fatec.chopperhousegames.core.domain.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
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
    private String cep;

    private String complemento;

    @Enumerated(EnumType.STRING)
    private TIPO_ENDERECO tipoEndereco;

    @Embedded
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}

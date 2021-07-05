package br.com.fatec.chopperhouse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco extends EntidadeDominio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cep;
    @Embedded
    private Cidade cidade;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tpEndereco;

}

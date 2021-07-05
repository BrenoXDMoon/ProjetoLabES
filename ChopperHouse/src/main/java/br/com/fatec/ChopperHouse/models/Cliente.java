package br.com.fatec.chopperhouse.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente extends EntidadeDominio{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeCompleto;
    private String email;
    private String dataNascimento;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @OneToMany
    private List<Documento> documentos;

    @OneToMany
    private List<Endereco> enderecos;

    @Embedded
    private Senha senha;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    private boolean ativo;
}

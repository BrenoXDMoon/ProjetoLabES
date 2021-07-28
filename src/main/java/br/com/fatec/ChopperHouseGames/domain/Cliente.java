package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Data
public class Cliente extends EntidadeDominio {

    private String nomeCompleto;
    private String email;
    private String dataNascimento;

    @Length(min = 6)
    private String senha;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Documento> documentos;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Endereco> enderecos;

    @ManyToOne
    private TipoCliente tipoCliente;

    private String telefone;

    private String roles;

    private boolean ativo;
}

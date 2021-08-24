package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cliente extends EntidadeDominio {

    private String nomeCompleto;
    private String email;
    private String dataNascimento;

    @Length(min = 6)
    private String senha;

    private String cpf;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartaoCredito> cartoes;

    @ManyToOne
    private TipoCliente tipoCliente;

    private String telefone;

    private String roles;

    private boolean ativo;
}

package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class TipoEndereco extends EntidadeDominio {

    private String descricao;
    private String nome;

    @OneToMany(targetEntity = Endereco.class)
    private List<Endereco> enderecos;
}

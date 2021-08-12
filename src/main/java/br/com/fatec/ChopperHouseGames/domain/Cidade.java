package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cidade extends EntidadeDominio{
    private String descricao;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Estado estado;
    @OneToMany
    private List<Endereco> enderecos;
}

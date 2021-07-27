package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Cidade extends EntidadeDominio{
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
    @OneToMany
    private List<Endereco> enderecos;
}

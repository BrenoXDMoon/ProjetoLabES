package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Plataforma extends EntidadeDominio{
    private String nome;

    @OneToMany(mappedBy = "plataforma", targetEntity = Jogo.class)
    private List<Jogo> jogo;
}

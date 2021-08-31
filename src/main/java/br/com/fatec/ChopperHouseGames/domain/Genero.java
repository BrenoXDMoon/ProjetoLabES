package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Genero extends EntidadeDominio{
    private String nome;

    @ManyToMany
    private List<Jogo> jogos;
}

package br.com.fatec.ChopperHouseGames.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Idioma extends EntidadeDominio{
    private String nome;

    @ManyToMany
    private List<Jogo> jogos;
}

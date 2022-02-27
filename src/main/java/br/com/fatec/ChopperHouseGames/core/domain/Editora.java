package br.com.fatec.ChopperHouseGames.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Editora extends EntidadeDominio{

    private String nome;
    @OneToMany(mappedBy = "editora", targetEntity = Jogo.class)
    private List<Jogo> jogos;
}

package br.com.fatec.chopperhousegames.core.domain.entity;

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

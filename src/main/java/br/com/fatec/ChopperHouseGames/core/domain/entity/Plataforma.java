package br.com.fatec.ChopperHouseGames.core.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Plataforma extends EntidadeDominio{
    private String nome;

    @OneToMany(mappedBy = "plataforma", targetEntity = Jogo.class)
    private List<Jogo> jogo;
}

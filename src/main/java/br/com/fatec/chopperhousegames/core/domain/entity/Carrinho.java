package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Carrinho extends EntidadeDominio{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
}

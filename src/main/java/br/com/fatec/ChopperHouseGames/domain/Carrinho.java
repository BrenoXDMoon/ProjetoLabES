package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Carrinho extends EntidadeDominio{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
}

package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
@Data
public class Estado {
    private String estado;
}

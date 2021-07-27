package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Estado extends EntidadeDominio {
    private String descricao;
    @OneToMany
    private List<Endereco> enderecos;
}

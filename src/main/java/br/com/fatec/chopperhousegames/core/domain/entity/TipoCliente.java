package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class TipoCliente extends EntidadeDominio{

    private String titulo;
    private String descricao;
    @OneToMany(targetEntity = Cliente.class)
    private List<Cliente> clientes;
}

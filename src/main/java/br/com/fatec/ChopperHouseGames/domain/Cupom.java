package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
public class Cupom extends EntidadeDominio {

    private String codigo;

    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    private Integer quantidade;

    @Min(value = 1, message = "O Valor deve ser maior que 0")
    private Double valor;

    @OneToMany(mappedBy = "cupom", targetEntity = Pedido.class)
    private List<Pedido> pedidos;
}

package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_cupom_ID")
    private TipoCupom tipoCupom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_ID")
    @Nullable
    private Cliente cliente;
}

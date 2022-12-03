package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
public class Cupom extends EntidadeDominio {

    private String codigo;

    private Integer quantidade;

    @Min(value = 1, message = "O Valor deve ser maior que 0")
    private Double valor;

    @OneToMany(mappedBy = "cupom", targetEntity = Pedido.class)
    private List<Pedido> pedidos;

    @Enumerated(EnumType.STRING)
    private TipoCupom tipoCupom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_ID")
    @Nullable
    private Cliente cliente;
}

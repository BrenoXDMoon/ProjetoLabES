package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends EntidadeDominio {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    private Double total;
    private Double frete;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "endereco_cobranca_id")
    private Endereco enderecoCobranca;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<MetodoPagamento> metodosPagamento;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Cupom cupom;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Cupom> cuponsTroca;

    @ManyToMany
    private List<Item> itens;

    @OneToOne(mappedBy = "pedido")
    private Devolucao devolucao;

    @Transient
    private Integer quantidade;

    public Boolean hasCupom() {
        return getCupom() != null && getCupom().getId() != null;
    }

    public Boolean hasCuponsTroca() {
        return getCuponsTroca() != null && !getCuponsTroca().isEmpty();
    }
}

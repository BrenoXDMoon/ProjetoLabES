package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pedido extends EntidadeDominio {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    private Double total;
    private String codigo;
    private Double frete;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "endereco_cobranca_id")
    private Endereco enderecoCobranca;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MetodoPagamento> metodosPagamento;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Cupom cupom;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Cupom> cuponsTroca;

    @ManyToMany
    private List<Item> itens;

    @OneToOne(mappedBy = "pedido")
    private Devolucao devolucao;

    @Transient
    private Integer quantidade;
}

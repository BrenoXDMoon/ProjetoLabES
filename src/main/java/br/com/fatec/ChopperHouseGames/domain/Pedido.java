package br.com.fatec.ChopperHouseGames.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @ManyToOne
    @JoinColumn(name = "status_id")
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
}

package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends EntidadeDominio {

    private String nomeCompleto;

    @Column(unique = true)
    private String email;
    private String dataNascimento;

    @Embedded
    private Senha senha;
    private String cpf;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartaoCredito> cartoes;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    private String telefone;

    private String roles;

    private boolean ativo;

    @OneToOne(cascade = CascadeType.ALL)
    private Carrinho carrinho;

    @OneToMany(mappedBy = "cliente", targetEntity = Pedido.class)
    private List<Pedido> pedidos;

    @Transient
    public List<Item> getItens() {
        return Optional.ofNullable(this.carrinho.getItens()).orElse(List.of());
    }
}
package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Jogo extends EntidadeDominio {

    private String titulo;
    private Double preco;
    private boolean multijogador;
    private String faixaEtaria;
    private String sinopse;
    private String imagem;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plataforma_id")
    private Plataforma plataforma;
    @ManyToMany
    private List<Idioma> idiomas;
    @ManyToMany
    private List<Genero> generos;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editora_id")
    private Editora editora;
    private boolean ativo;
    private Integer quantidade;
    private Integer quantidadeDisponivel;
    private String motivoInativacao;
}

package br.com.fatec.ChopperHouseGames.dto;

import br.com.fatec.ChopperHouseGames.domain.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class JogoDto {

    @NotEmpty(message = "O Jogo deve conter um título")
    private String titulo;
    @NotEmpty(message = "O valor não pode ser nulo")
    private Double preco;
    @NotEmpty(message = "O Jogo deve especificar se possui multiplayer")
    private boolean multijogador;
    @NotEmpty(message = "O jogo deve conter faixa etária minima")
    private String faixaEtaria;
    @NotEmpty(message = "O Jogo deve conter uma sinopse")
    private String sinopse;
    @NotEmpty(message = "O Jogo deve conter ao menos uma imagem")
    private String imagem;
    @NotEmpty(message = "O Jogo deve conter uma data de lançamento")
    private Date dataLancamento;
    @NotEmpty(message = "O Jogo deve conter ao menos uma plataforma")
    private Plataforma plataforma;
    @NotEmpty(message = "O Jogo deve conter ao menos um idioma")
    private List<Idioma> idiomas;
    @NotEmpty(message = "O Jogo deve conter ao menos um gênero")
    private List<Genero> generos;
    @NotEmpty(message = "O Jogo deve conter uma editora")
    private Editora editora;
    @NotEmpty(message = "O Jogo deve conter quantidade não vazia")
    private Integer quantidade;
    @NotEmpty(message = "O Jogo deve conter quantidade disponível não vazia")
    private Integer quantidadeDisponivel;
    private String motivoInativacao;

    public Jogo toJogo() {
        Jogo jogo = new Jogo();
        jogo.setTitulo(this.titulo);
        jogo.setPreco(this.preco);
        jogo.setMultijogador(this.multijogador);
        jogo.setFaixaEtaria(this.faixaEtaria);
        jogo.setSinopse(this.sinopse);
        jogo.setImagem(this.imagem);
        jogo.setDataLancamento(this.dataLancamento);
        jogo.setPlataforma(this.plataforma);
        jogo.setIdiomas(this.idiomas);
        jogo.setGeneros(this.generos);
        jogo.setEditora(this.editora);
        jogo.setQuantidade(this.quantidade);
        jogo.setQuantidadeDisponivel(this.quantidadeDisponivel);
        jogo.setMotivoInativacao(this.motivoInativacao);
        return jogo;
    }
}

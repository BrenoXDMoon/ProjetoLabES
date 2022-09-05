package br.com.fatec.chopperhousegames.inbound.facade.dto;

import br.com.fatec.chopperhousegames.core.domain.entity.Editora;
import br.com.fatec.chopperhousegames.core.domain.entity.Genero;
import br.com.fatec.chopperhousegames.core.domain.entity.Idioma;
import br.com.fatec.chopperhousegames.core.domain.entity.Plataforma;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class JogoDTO {

    @NotEmpty(message = "O Jogo deve conter um título")
    private String titulo;
    @Min(value = 1, message = "O preço míinimo do jogo deve ser R$1")
    private Double preco;
    private boolean multijogador;
    @NotEmpty(message = "O jogo deve conter faixa etária minima")
    private String faixaEtaria;
    @NotEmpty(message = "O Jogo deve conter uma sinopse")
    private String sinopse;
    @NotEmpty(message = "O Jogo deve conter ao menos uma imagem")
    private String imagem;
    private String dataLancamento;
    private Plataforma plataforma;
    private List<Idioma> idiomas;
    private List<Genero> generos;
    private Editora editora;
    @Min(value = 0, message = "O jogo deve ter quantidade mínima de 0")
    private Integer quantidade;
    private Integer quantidadeDisponivel;
    private String motivoInativacao;
    private boolean ativo;

}

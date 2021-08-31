package br.com.fatec.ChopperHouseGames.dto;

import br.com.fatec.ChopperHouseGames.domain.Editora;
import br.com.fatec.ChopperHouseGames.domain.Genero;
import br.com.fatec.ChopperHouseGames.domain.Idioma;
import br.com.fatec.ChopperHouseGames.domain.Plataforma;
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

    @NotEmpty
    private Date dataLancamento;

    private Plataforma plataforma;

    private List<Idioma> idiomas;

    private List<Genero> generos;

    private Editora editora;

    private boolean ativo;

    private Integer quantidade;

    private Integer quantidadeDisponivel;

    private String motivoInativacao;
}

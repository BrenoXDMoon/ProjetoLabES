package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class TipoDocumento extends EntidadeDominio {

    @NotNull(message = "Tipo do documento não pode ser branco")
    @NotBlank(message = "Tipo do documento não pode ser branco")
    private String nome;

    @NotNull(message = "Descrição do documento não pode ser nulo")
    @NotBlank(message = "Descrição do documento não pode ser branco")
    private String descricao;

    @OneToMany(targetEntity = Documento.class)
    private List<Documento> documents;
}

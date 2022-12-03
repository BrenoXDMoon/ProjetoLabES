package br.com.fatec.chopperhousegames.inbound.facade.dto;

import br.com.fatec.chopperhousegames.core.domain.entity.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoDTO extends EntidadeDTO {

    private String mensagem;
    private List<EntidadeDominio> entidades;
    private EntidadeDominio entidade;

}

package br.com.fatec.chopperhousegames.inbound.facade.dto;

import lombok.Data;

@Data
public class FiltroDto {
    private String dataInicio;
    private String dataFim;
    private Integer tipoConsulta;
}

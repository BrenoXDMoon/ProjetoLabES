package br.com.fatec.ChopperHouseGames.dto;

import lombok.Data;

@Data
public class FiltroDto {
    private String dataInicio;
    private String dataFim;
    private Integer tipoConsulta;
}

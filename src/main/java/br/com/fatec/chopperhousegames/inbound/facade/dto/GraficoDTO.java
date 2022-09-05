package br.com.fatec.chopperhousegames.inbound.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class GraficoDTO {
    private List<String> label;
    private List<DataSetDTO> datasets;
}

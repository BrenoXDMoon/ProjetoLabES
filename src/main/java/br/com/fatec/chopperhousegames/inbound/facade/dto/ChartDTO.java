package br.com.fatec.chopperhousegames.inbound.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChartDTO {
    private List<String> label;
    private List<DataSetDTO> datasets;
}

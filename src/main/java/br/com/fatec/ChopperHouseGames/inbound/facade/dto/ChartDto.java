package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChartDto {
    private List<String> label;
    private List<DataSetDto> datasets;
}

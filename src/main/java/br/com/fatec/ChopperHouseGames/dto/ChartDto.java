package br.com.fatec.ChopperHouseGames.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChartDto {
    private List<String> label;
    private List<DataSetDto> datasets;
}

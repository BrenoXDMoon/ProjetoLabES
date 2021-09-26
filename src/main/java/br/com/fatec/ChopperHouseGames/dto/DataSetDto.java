package br.com.fatec.ChopperHouseGames.dto;

import java.util.List;
import java.util.Random;

public class DataSetDto {
    private String label;
    private List<Double> data ;
    private String borderColor;
    private String backgroundColor;

    public DataSetDto() {
        Random random = new Random();
        this.borderColor = "#" + random.nextInt(900) + 100;
        this.backgroundColor = "rgba(255,255,255,0)";
    }
}

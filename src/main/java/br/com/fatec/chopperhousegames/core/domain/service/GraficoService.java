package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.inbound.facade.dto.ChartDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface GraficoService {
    ChartDTO buscarTodosCriadosEntre(LocalDate dataInicial, LocalDate dataFinal, Integer tipoBusca);

    List<HashMap<String, Double>> preencherIndexCards();
}

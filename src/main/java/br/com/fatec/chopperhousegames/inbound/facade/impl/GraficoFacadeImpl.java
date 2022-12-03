package br.com.fatec.chopperhousegames.inbound.facade.impl;

import br.com.fatec.chopperhousegames.core.domain.service.GraficoService;
import br.com.fatec.chopperhousegames.inbound.facade.GraficoFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ChartDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class GraficoFacadeImpl implements GraficoFacade {
    GraficoService service;

    @Override
    public ChartDTO buscarTodosCriadosEntre(LocalDate dataInicial, LocalDate dataFinal, Integer tipoBusca) {
        return service.buscarTodosCriadosEntre(dataInicial, dataFinal, tipoBusca);
    }

    @Override
    public List<HashMap<String, Double>> preencherIndexCards() {
        return service.preencherIndexCards();
    }
}

package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Pedido;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ChartDto;
import br.com.fatec.chopperhousegames.inbound.facade.dto.GraficoDto;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface PedidoService {

    Pedido buscarById(Integer id);
    Pedido salvar(Pedido pedido, BindingResult result);
    List<Pedido> buscarTodos();
    Pedido editar(Pedido pedido);
    GraficoDto findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType);
    List<Pedido> buscarByStatus(String status);
    ChartDto buscarTodosCriadosEntre(Date dataInicial, Date dataFinal, Integer tipoBusca);
    List<HashMap<String, Double>> preencherIndexCards();
}

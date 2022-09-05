package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Pedido;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ChartDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.GraficoDTO;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface PedidoService {

    Pedido buscarById(Integer id);
    Pedido salvar(Pedido pedido, BindingResult result);
    List<Pedido> buscarTodos();
    Pedido editar(Pedido pedido);
    GraficoDTO findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType);
    List<Pedido> buscarByStatus(String status);
    ChartDTO buscarTodosCriadosEntre(Date dataInicial, Date dataFinal, Integer tipoBusca);
    List<HashMap<String, Double>> preencherIndexCards();
}

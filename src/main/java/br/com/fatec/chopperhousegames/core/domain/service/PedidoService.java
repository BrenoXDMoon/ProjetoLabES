package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Pedido;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

public interface PedidoService {

    Pedido buscarById(Integer id);
    Pedido salvar(Pedido pedido, BindingResult result);
    Pedido editar(Pedido pedido);

    List<Pedido> buscarPedidosEntre(LocalDate dataInicial, LocalDate dataFinal);

    List<Pedido> buscarTodosPedidos();
}

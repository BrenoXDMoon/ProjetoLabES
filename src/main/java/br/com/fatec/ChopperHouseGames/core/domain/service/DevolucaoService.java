package br.com.fatec.ChopperHouseGames.core.domain.service;

import br.com.fatec.ChopperHouseGames.core.domain.entity.Devolucao;
import br.com.fatec.ChopperHouseGames.core.domain.entity.Pedido;

import java.util.List;

public interface DevolucaoService {

    Devolucao salvarSolicitacaoDevolucao(Devolucao devolucao);
    void editar(Devolucao devolucao);
    List<Devolucao> buscarTodos();
    Devolucao buscaDevolucaoByPedidoId(Pedido pedido);
    Devolucao buscarById(Integer id);
    Devolucao aceitar(Devolucao devolucao);
    Devolucao recusar(Devolucao devolucao);
    Devolucao processar(Devolucao devolucao);
    Devolucao receber(Devolucao devolucao);
    Devolucao finalizar(Devolucao devolucao);
}

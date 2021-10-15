package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.Devolucao;
import br.com.fatec.ChopperHouseGames.domain.Pedido;

import java.util.List;

public interface IDevolucaoService {

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

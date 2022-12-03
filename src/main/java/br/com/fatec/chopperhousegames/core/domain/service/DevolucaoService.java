package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Devolucao;
import br.com.fatec.chopperhousegames.core.domain.entity.Pedido;

import java.util.List;

public interface DevolucaoService {

    Devolucao salvarSolicitacaoDevolucao(Devolucao devolucao);
    void editarDevolucao(Devolucao devolucao);
    List<Devolucao> buscarTodasDevolucoes();
    Devolucao buscaDevolucaoPorIdDePedido(Pedido pedido);
    Devolucao buscarDevolucaoPorId(Long id);
    Devolucao aceitarDevolucao(Devolucao devolucao);
    Devolucao recusarDevolucao(Devolucao devolucao);
    Devolucao processarDevolucao(Devolucao devolucao);
    Devolucao receberDevolucao(Devolucao devolucao);
    Devolucao finalizarDevolucao(Devolucao devolucao);
}

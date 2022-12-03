package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.*;
import br.com.fatec.chopperhousegames.core.domain.service.CupomService;
import br.com.fatec.chopperhousegames.core.domain.service.DevolucaoService;
import br.com.fatec.chopperhousegames.core.domain.service.JogoService;
import br.com.fatec.chopperhousegames.core.repository.DevolucaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class DevolucaoServiceImpl implements DevolucaoService {

    private final DevolucaoRepository repository;
    private final CupomService cupomService;
    private final JogoService jogoService;

    public DevolucaoServiceImpl(DevolucaoRepository repository, CupomService cupomService, JogoService jogoService) {
        this.repository = repository;
        this.cupomService = cupomService;
        this.jogoService = jogoService;
    }

    @Override
    public Devolucao salvarSolicitacaoDevolucao(Devolucao devolucao) {
        devolucao.getPedido().setStatus(Status.TROCA_SOLICITADA);
        devolucao.setStatusDevolucao(StatusDevolucao.AGUARDANDO_RESPOSTA);
        return repository.saveAndFlush(devolucao);
    }

    @Override
    public void editarDevolucao(Devolucao devolucao) {
        repository.saveAndFlush(devolucao);
    }

    @Override
    public List<Devolucao> buscarTodasDevolucoes() {
        return repository.findAll();
    }

    @Override
    public Devolucao buscaDevolucaoPorIdDePedido(Pedido pedido) {
        return repository.findByPedido_Id(pedido.getId()).orElseThrow(() -> new RuntimeException("Devolução não encontrada para o pedido informado"));
    }

    @Override
    public Devolucao buscarDevolucaoPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Devolucao aceitarDevolucao(Devolucao devolucao) {

        devolucao = repository.findById(devolucao.getId()).orElseThrow(() -> new RuntimeException("Devolução não encontrada para o pedido de devolução informado"));
        devolucao.getPedido().setStatus(Status.TROCA_APROVADA);
        devolucao.setStatusDevolucao(StatusDevolucao.ACEITO);
        return repository.saveAndFlush(devolucao);
    }

    @Override
    public Devolucao recusarDevolucao(Devolucao devolucao) {

        devolucao = repository.findById(devolucao.getId()).get();
        devolucao.getPedido().setStatus(Status.TROCA_RECUSADA);
        devolucao.setStatusDevolucao(StatusDevolucao.RECUSADO);
        return repository.saveAndFlush(devolucao);

    }

    @Override
    public Devolucao processarDevolucao(Devolucao devolucao) {
        devolucao = repository.findById(devolucao.getId()).orElseThrow(() -> new RuntimeException("Devolução não encontrada"));
        devolucao.getPedido().setStatus(Status.PRODUTO_ENVIADO_PARA_TROCA);
        devolucao.setStatusDevolucao(StatusDevolucao.EM_PROCESSAMENTO);
        return repository.saveAndFlush(devolucao);
    }

    @Override
    public Devolucao receberDevolucao(Devolucao devolucao){
        devolucao = repository.findById(devolucao.getId()).get();
        devolucao.getPedido().setStatus(Status.PRODUTO_RECEBIDO_PARA_TROCA);
        devolucao.setStatusDevolucao(StatusDevolucao.EM_PROCESSAMENTO);

        return repository.saveAndFlush(devolucao);
    }

    @Override
    public Devolucao finalizarDevolucao(Devolucao devolucao){
        devolucao = repository.findById(devolucao.getId()).orElseThrow(() -> new RuntimeException("Devolução não encontrada"));
        devolucao.getPedido().setStatus(Status.TROCA_REALIZADA);
        devolucao.setStatusDevolucao(StatusDevolucao.FINALIZADO);
        Double valorDeRetiradaDoPedido = 0.0;
        for(Item item : devolucao.getPedido().getItens()){
            //devolvendo produto ao estoque
            item.getJogo().setQuantidadeDisponivel(item.getJogo().getQuantidadeDisponivel() + item.getQuantidade());
            jogoService.salvarJogo(item.getJogo());

            if(item.getQuantidadeTroca() != null && item.getQuantidadeTroca() > 0){
                //pega o valor do jogo e multiplica pela quantidade que está sendo recebida para troca
                valorDeRetiradaDoPedido += item.getJogo().getPreco() * item.getQuantidadeTroca();
                //atualiza a quantidade de itens efetivos do pedido
                item.setQuantidade(item.getQuantidade()-item.getQuantidadeTroca());
            }
        }
        //atualiza valor total do pedido com base no valor total dos produtos sendo devolvidos
        devolucao.getPedido().setTotal(devolucao.getPedido().getTotal() - valorDeRetiradaDoPedido);
        gerarCupomTrocaAleatorio(devolucao, valorDeRetiradaDoPedido);
        return repository.saveAndFlush(devolucao);
    }

    private void gerarCupomTrocaAleatorio(Devolucao devolucao, Double valor){

        cupomService.salvarCupomTroca(devolucao.getPedido().getCliente(), valor);
    }
}

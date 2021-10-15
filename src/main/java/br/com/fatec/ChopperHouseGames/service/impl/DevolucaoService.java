package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.*;
import br.com.fatec.ChopperHouseGames.repository.CupomRepository;
import br.com.fatec.ChopperHouseGames.repository.DevolucaoRepository;
import br.com.fatec.ChopperHouseGames.repository.StatusRepository;
import br.com.fatec.ChopperHouseGames.repository.TipoCupomRepository;
import br.com.fatec.ChopperHouseGames.service.IDevolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class DevolucaoService implements IDevolucaoService {

    @Autowired
    DevolucaoRepository repository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    CupomRepository cupomRepository;

    @Autowired
    TipoCupomRepository tipoCupomRepository;

    @Override
    public Devolucao salvarSolicitacaoDevolucao(Devolucao devolucao) {
        devolucao.getPedido().setStatus(statusRepository.findByStatus("TROCA SOLICITADA"));
        devolucao.setStatusDevolucao(StatusDevolucao.AGUARDANDO_RESPOSTA);
        return repository.saveAndFlush(devolucao);
    }

    @Override
    public void editar(Devolucao devolucao) {
        repository.saveAndFlush(devolucao);
    }

    @Override
    public List<Devolucao> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Devolucao buscaDevolucaoByPedidoId(Pedido pedido) {
        return repository.findByPedido_Id(pedido.getId());
    }

    @Override
    public Devolucao buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Devolucao aceitar(Devolucao devolucao) {

        devolucao = repository.findById(devolucao.getId()).get();
        devolucao.getPedido().setStatus(statusRepository.findByStatus("TROCA ACEITA"));
        devolucao.setStatusDevolucao(StatusDevolucao.ACEITO);
        return repository.saveAndFlush(devolucao);
    }

    @Override
    public Devolucao recusar(Devolucao devolucao) {

        devolucao = repository.findById(devolucao.getId()).get();
        devolucao.getPedido().setStatus(statusRepository.findByStatus("TROCA RECUSADA"));
        devolucao.setStatusDevolucao(StatusDevolucao.RECUSADO);
        return repository.saveAndFlush(devolucao);

    }

    @Override
    public Devolucao processar(Devolucao devolucao) {
        devolucao = repository.findById(devolucao.getId()).get();
        devolucao.getPedido().setStatus(statusRepository.findByStatus("PRODUTO ENVIADO PARA TROCA"));
        devolucao.setStatusDevolucao(StatusDevolucao.EM_PROCESSAMENTO);
        return repository.saveAndFlush(devolucao);
    }

    @Override
    public Devolucao receber(Devolucao devolucao){
        devolucao = repository.findById(devolucao.getId()).get();
        devolucao.getPedido().setStatus(statusRepository.findByStatus("PRODUTO RECEBIDO PARA TROCA"));
        devolucao.setStatusDevolucao(StatusDevolucao.EM_PROCESSAMENTO);
        return repository.saveAndFlush(devolucao);
    }

    @Override//gerar cupom de troca
    public Devolucao finalizar(Devolucao devolucao){
        devolucao = repository.findById(devolucao.getId()).get();
        devolucao.getPedido().setStatus(statusRepository.findByStatus("TROCA REALIZADA"));
        devolucao.setStatusDevolucao(StatusDevolucao.FINALIZADO);
        gerarCupomTrocaAleatorio(devolucao);
        return repository.saveAndFlush(devolucao);
    }

    private void gerarCupomTrocaAleatorio(Devolucao devolucao){
        Cupom cupom = new Cupom();

        Random random = new Random();

        String codigo = random.ints(48, 122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        cupom.setCodigo(codigo);
        cupom.setQuantidade(1);
        cupom.setCliente(devolucao.getPedido().getCliente());
        cupom.setTipoCupom(tipoCupomRepository.findByNome("TROCA"));
        cupom.setValor(devolucao.getPedido().getTotal());

        cupomRepository.saveAndFlush(cupom);
    }
}

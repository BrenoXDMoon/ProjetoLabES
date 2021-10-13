package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Cupom;
import br.com.fatec.ChopperHouseGames.domain.Devolucao;
import br.com.fatec.ChopperHouseGames.domain.StatusDevolucao;
import br.com.fatec.ChopperHouseGames.domain.TipoCupom;
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
        StringBuilder buffer = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomLimitedInt = 97 + (int)
                    (random.nextFloat() * (122 - 97 + 1));
            buffer.append((char) randomLimitedInt);
        }

        cupom.setCodigo(buffer.toString());
        cupom.setQuantidade(1);
        cupom.setCliente(devolucao.getPedido().getCliente());
        cupom.setTipoCupom(tipoCupomRepository.findByNome("TROCA"));
        cupom.setValor(devolucao.getPedido().getTotal());

        cupomRepository.saveAndFlush(cupom);
    }
}

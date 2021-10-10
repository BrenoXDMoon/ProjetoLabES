package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Devolucao;
import br.com.fatec.ChopperHouseGames.domain.StatusDevolucao;
import br.com.fatec.ChopperHouseGames.repository.DevolucaoRepository;
import br.com.fatec.ChopperHouseGames.repository.StatusRepository;
import br.com.fatec.ChopperHouseGames.service.IDevolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DevolucaoService implements IDevolucaoService {

    @Autowired
    DevolucaoRepository repository;
    @Autowired
    StatusRepository statusRepository;

    @Override
    public Devolucao salvarSolicitacaoDevolucao(Devolucao devolucao) {
        devolucao.getPedido().setStatus(statusRepository.findByStatus("TROCA SOLICITADA"));
        devolucao.setStatusDevolucao(StatusDevolucao.AGUARDANDO_RESPOSTA);
        return repository.saveAndFlush(devolucao);
    }
}

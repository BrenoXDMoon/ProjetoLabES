package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Endereco;
import br.com.fatec.ChopperHouseGames.core.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements br.com.fatec.ChopperHouseGames.core.service.EnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Override
    public Endereco buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Endereco salvar(Endereco end) {

        Endereco endereco = repository.saveAndFlush(end);
        return endereco;
    }
}

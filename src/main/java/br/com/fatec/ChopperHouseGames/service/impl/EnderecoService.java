package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Endereco;
import br.com.fatec.ChopperHouseGames.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.service.IEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService implements IEnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Override
    public Endereco buscarById(Integer id) {
        return repository.findById(id).get();
    }
}

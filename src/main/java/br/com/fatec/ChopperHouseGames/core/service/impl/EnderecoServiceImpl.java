package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Endereco;
import br.com.fatec.ChopperHouseGames.core.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.core.service.EnderecoService;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Override
    public Endereco buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Endereco salvar(Endereco end) {

        return repository.saveAndFlush(end);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Endereco editar(ClienteDTO atualUsuarioLogado, Endereco endereco) {
        return repository.saveAndFlush(endereco);
    }
}

package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Endereco;
import br.com.fatec.chopperhousegames.core.repository.EnderecoRepository;
import br.com.fatec.chopperhousegames.core.domain.service.EnderecoService;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Override
    public Endereco buscarEnderecoPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Endereco salvarEndereco(Endereco end) {

        return repository.saveAndFlush(end);
    }

    @Override
    public void excluirEndereco(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Endereco editarEndereco(ClienteDTO atualUsuarioLogado, Endereco endereco) {
        return repository.saveAndFlush(endereco);
    }
}

package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import br.com.fatec.chopperhousegames.core.domain.service.JogoService;
import br.com.fatec.chopperhousegames.core.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoServiceImpl implements JogoService {

    @Autowired
    JogoRepository repository;

    @Override
    public void salvarJogo(Jogo jogo) {
        repository.saveAndFlush(jogo);
    }

    @Override
    public void editarJogo(Jogo jogo) {
        repository.saveAndFlush(jogo);
    }

    @Override
    public void excluirJogo(Jogo jogo) {

        this.buscarJogoPorId(jogo.getId());

        jogo.setAtivo(false);
        repository.saveAndFlush(jogo);
    }

    @Override
    public void ativarJogo(Jogo jogo) {
        jogo.setAtivo(true);
        repository.saveAndFlush(jogo);
    }

    @Override
    public List<Jogo> listarTodosJogos() {
        return repository.findAll();
    }

    @Override
    public List<Jogo> listarJogosAtivos() {
        return repository.findAllByAtivoTrue();
    }

    @Override
    public Optional<Jogo> buscarJogoPorId(Long id) {
        return repository.findById(id);
    }
}
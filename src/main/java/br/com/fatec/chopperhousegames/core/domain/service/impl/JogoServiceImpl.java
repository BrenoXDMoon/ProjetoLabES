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
    public Jogo salvarJogo(Jogo jogo) {

        try{
            repository.saveAndFlush(jogo);
            return jogo;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Jogo editarJogo(Jogo jogo) {
        try{
            repository.saveAndFlush(jogo);
            return jogo;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Jogo excluirJogo(Jogo jogo) {

        this.buscarJogoPorId(jogo.getId());

            jogo.setAtivo(false);
            repository.saveAndFlush(jogo);
            return jogo;
    }

    @Override
    public Jogo ativarJogo(Jogo jogo) {
        try{
            jogo.setAtivo(true);
            repository.saveAndFlush(jogo);
            return jogo;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Jogo> listarJogo() {
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
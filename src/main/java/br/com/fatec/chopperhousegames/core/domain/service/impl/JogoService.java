package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import br.com.fatec.chopperhousegames.core.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService implements br.com.fatec.chopperhousegames.core.domain.service.JogoService {

    @Autowired
    JogoRepository repository;

    @Override
    public Jogo salvar(Jogo jogo) {

        try{
            repository.saveAndFlush(jogo);
            return jogo;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Jogo editar(Jogo jogo) {
        try{
            repository.saveAndFlush(jogo);
            return jogo;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Jogo excluir(Jogo jogo) {
        try{
            jogo.setAtivo(false);
            repository.saveAndFlush(jogo);
            return jogo;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Jogo ativar(Jogo jogo) {
        try{
            jogo.setAtivo(true);
            repository.saveAndFlush(jogo);
            return jogo;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Jogo> listar() {
        return repository.findAll();
    }

    @Override
    public List<Jogo> listarAtivos() {
        return repository.findAllByAtivoTrue();
    }

    @Override
    public Jogo buscarById(Integer id) {
        Optional optional = repository.findById(id);

        if (optional.isPresent()){
            return (Jogo) optional.get();
        }else{
            return null;
        }
    }
}
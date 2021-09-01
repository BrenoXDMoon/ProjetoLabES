package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Jogo;
import br.com.fatec.ChopperHouseGames.repository.JogoRepository;
import br.com.fatec.ChopperHouseGames.service.IJogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements IJogoService {

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
    public List<Jogo> listar() {
        return repository.findAll();
    }

    @Override
    public List<Jogo> listarAtivos() {
        return repository.findAllByAtivoTrue();
    }

    @Override
    public Jogo buscarById(Jogo jogo) {
        Optional optional = repository.findById(jogo.getId());

        if (optional.isPresent()){
            return (Jogo) optional.get();
        }else{
            return null;
        }
    }
}
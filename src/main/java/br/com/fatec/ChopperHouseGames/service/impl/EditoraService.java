package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.controller.IEditoraService;
import br.com.fatec.ChopperHouseGames.domain.Editora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService implements IEditoraService {

    @Autowired
    EditoraRepository repository;

    @Override
    public List<Editora> listar() {
        return repository.findAll();
    }
}

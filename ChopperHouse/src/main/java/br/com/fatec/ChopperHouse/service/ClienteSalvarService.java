package br.com.fatec.chopperhouse.service;

import br.com.fatec.chopperhouse.models.Cliente;
import br.com.fatec.chopperhouse.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteSalvarService {

    @Autowired
    ClienteRepository repository;

    public Cliente salvar(Cliente cliente){
        return repository.save(cliente);
    }

}

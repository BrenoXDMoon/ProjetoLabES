package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.service.TipoClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class ClienteServiceImplTest {

    @Mock
    ClienteRepository repository;

    @Mock
    TipoClienteService tipoClienteService;

    private ClienteServiceImpl clienteService;

    void init() {
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteServiceImpl(repository, tipoClienteService);
    }

    @Test
    void listar() {
        List<Cliente> clientes = new ArrayList<>();
        clienteService.listar().stream().filter(cliente -> (cliente.getId() % 2) != 0).forEach(clientes::add);
    }
}
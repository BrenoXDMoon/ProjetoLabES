package br.com.fatec.chopperhousegames.core.service.impl;

import br.com.fatec.chopperhousegames.core.domain.service.impl.ClienteServiceImpl;
import br.com.fatec.chopperhousegames.core.repository.ClienteRepository;
import br.com.fatec.chopperhousegames.core.domain.service.TipoClienteService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    //TODO - IMPLEMENTAR TESTES
}
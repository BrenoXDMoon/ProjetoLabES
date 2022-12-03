package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.CartaoCredito;
import br.com.fatec.chopperhousegames.core.domain.service.CartaoService;
import br.com.fatec.chopperhousegames.core.domain.service.ClienteService;
import br.com.fatec.chopperhousegames.core.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoServiceImpl implements CartaoService {


    private final CartaoCreditoRepository repository;
    private final ClienteService clienteService;

    public CartaoServiceImpl(CartaoCreditoRepository repository, ClienteService clienteService) {
        this.repository = repository;
        this.clienteService = clienteService;
    }

    @Override
    public CartaoCredito buscarCartaoPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
    }


    @Override
    public List<CartaoCredito> listarTodosOsCartoes() {
        return repository.findAll();
    }

    @Override
    public CartaoCredito salvarCartao(CartaoCredito cartao) {
        cartao.setCliente(clienteService.atualClienteLogado());
        return repository.saveAndFlush(cartao);
    }

    @Override
    public void excluirCartao(Long id) {
        repository.delete(buscarCartaoPorId(id));
    }
}

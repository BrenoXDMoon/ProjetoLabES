package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.CartaoCredito;
import br.com.fatec.chopperhousegames.core.domain.service.ClienteService;
import br.com.fatec.chopperhousegames.core.repository.CartaoCreditoRepository;
import br.com.fatec.chopperhousegames.core.domain.service.CartaoService;
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
    public CartaoCredito buscarPorId(Long id) {
        return repository.findById(id).orElse(new CartaoCredito());
    }


    @Override
    public List<CartaoCredito> listar() {
        return repository.findAll();
    }

    @Override
    public CartaoCredito salvar(CartaoCredito cartao) {
        cartao.setCliente(clienteService.atualUsuarioLogado());
        return repository.saveAndFlush(cartao);
    }

    @Override
    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }

    @Override
    public CartaoCredito editar(CartaoCredito cartao) {
        cartao.setCliente(clienteService.atualUsuarioLogado());
        return repository.saveAndFlush(cartao);
    }
}

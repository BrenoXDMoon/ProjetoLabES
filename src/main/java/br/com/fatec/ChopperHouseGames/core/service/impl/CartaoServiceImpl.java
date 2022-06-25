package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.CartaoCredito;
import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.core.service.CartaoService;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
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

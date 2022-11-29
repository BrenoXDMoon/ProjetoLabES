package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Cliente;

public interface CarrinhoService {
     void adicionarItemCarrinho(Cliente cliente, Long idJogo, Integer quantidade);
    void removerItemCarrinho(Cliente cliente, Long itemId);
}

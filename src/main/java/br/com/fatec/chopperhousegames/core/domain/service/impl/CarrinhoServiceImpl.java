package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Carrinho;
import br.com.fatec.chopperhousegames.core.domain.entity.Cliente;
import br.com.fatec.chopperhousegames.core.domain.entity.Item;
import br.com.fatec.chopperhousegames.core.domain.entity.Jogo;
import br.com.fatec.chopperhousegames.core.domain.service.CarrinhoService;
import br.com.fatec.chopperhousegames.core.repository.ClienteRepository;
import br.com.fatec.chopperhousegames.core.repository.JogoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CarrinhoServiceImpl implements CarrinhoService {

    private final ClienteRepository clienteRepository;

    private final JogoRepository jogoRepository;

    public CarrinhoServiceImpl(ClienteRepository clienteRepository, JogoRepository jogoRepository) {
        this.clienteRepository = clienteRepository;
        this.jogoRepository = jogoRepository;
    }

    @Override
    public void adicionarItemCarrinho(Cliente cliente, Long idJogo, Integer quantidade) {
        Carrinho carrinho;
        List<Item> itens;

        boolean validaExistencia = false;

        if (cliente.getCarrinho() == null) {//se o cliente não tem carrinho, cria carrinho com uma nova lista de itens
            carrinho = new Carrinho();
            itens = new ArrayList<>();
        } else {  //se ele já tem carrinho, use o carrinho dele com a lista de itens já existentes
            carrinho = cliente.getCarrinho();
            itens = cliente.getCarrinho().getItens();
        }
        Jogo jogo = jogoRepository.findById(idJogo).orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
        jogo.setQuantidadeDisponivel(jogo.getQuantidade() - quantidade);

        for (Item i : itens) {
            if (i.getJogo().getId().equals(jogo.getId())) {
                log.info("ENCONTRADA EXISTÊNCIA DE PRODUTO NO CARRINHO");
                validaExistencia = true;
                i.setQuantidade(i.getQuantidade() + quantidade);
                break;
            }
        }

        if (!validaExistencia) {  //se o item não existir na lista, criamos uma item novo e adicionamos a lista
            Item item = new Item();
            item.setJogo(jogo);
            item.setQuantidade(quantidade);
            itens.add(item);
        }

        carrinho.setItens(itens);

        cliente.setCarrinho(carrinho);

        clienteRepository.saveAndFlush(cliente);

        jogoRepository.saveAndFlush(jogo);
    }

    @Override
    public void removerItemCarrinho(Cliente cliente, Long itemId) {
        //Aplico um filtro buscando pelo id do item que eu quero remover
        cliente.getItens().stream().filter(item -> item.getId().equals(itemId)).forEach(item -> {
            item.getJogo().setQuantidadeDisponivel(item.getJogo().getQuantidadeDisponivel() + item.getQuantidade());
            cliente.getCarrinho().getItens().remove(item);
        });
        clienteRepository.saveAndFlush(cliente);
    }
}
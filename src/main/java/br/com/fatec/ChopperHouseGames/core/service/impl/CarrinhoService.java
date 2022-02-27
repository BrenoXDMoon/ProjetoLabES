package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Carrinho;
import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.Item;
import br.com.fatec.ChopperHouseGames.core.domain.Jogo;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.core.repository.JogoRepository;
import br.com.fatec.ChopperHouseGames.core.service.ICarrinhoService;
import br.com.fatec.ChopperHouseGames.core.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoService implements ICarrinhoService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    JogoRepository jogoRepository;

    @Autowired
    IClienteService clienteService;

    @Override
    public void adicionarItemCarrinho(Cliente cliente, Integer idJogo, Integer quantidade) {
        Carrinho carrinho = null;
        List<Item> itens = null;

        boolean validaExistencia = false;

        if(cliente.getCarrinho() == null){//se o cliente não tem carrinho, cria carrinho com uma nova lista de itens
            carrinho = new Carrinho();
            itens = new ArrayList<Item>();
        }else{  //se ele já tem carrinho, use o carrinho dele com a lista de itens já existentes
            carrinho = cliente.getCarrinho();
            itens = cliente.getCarrinho().getItens();
        }
        Jogo jogo = jogoRepository.findById(idJogo).get();
        jogo.setQuantidadeDisponivel(jogo.getQuantidade()-quantidade);

        for(Item i : itens){
            if(i.getJogo().getId().equals(jogo.getId())){   //encontrou exisência de um produto do mesmo id nos itens do carrinho
                System.out.println("ENCONTRADA EXISTÊNCIA DE PRODUTO NO CARRINHO");
                validaExistencia = true;
                i.setQuantidade(i.getQuantidade()+quantidade);
                break;
            }
        }

        if(!validaExistencia){  //se o item não existir na lista, criamos uma item novo e adicionamos a lista
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
    public void removerItemCarrinho(Cliente cliente, Integer itemId) {

        for(Item i : cliente.getCarrinho().getItens()){
            Item item = i;

            if(item.getId().equals(itemId)){
                item.getJogo().setQuantidadeDisponivel(item.getJogo().getQuantidadeDisponivel() + item.getQuantidade());
                cliente.getCarrinho().getItens().remove(i);
                break;
            }
        }
        clienteRepository.saveAndFlush(cliente);
    }
}
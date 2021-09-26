package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Pedido;
import br.com.fatec.ChopperHouseGames.domain.Status;
import br.com.fatec.ChopperHouseGames.dto.GraficoDto;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.repository.PedidoRepository;
import br.com.fatec.ChopperHouseGames.repository.StatusRepository;
import br.com.fatec.ChopperHouseGames.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    CartaoCreditoRepository cartaoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Pedido buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Pedido salvar(Pedido pedido, BindingResult result) {
        
        pedido = preencherPedido(pedido, result);

        if(result.hasErrors()){
            return pedido;
        }

        repository.saveAndFlush(pedido);

        pedido.getCliente().getCarrinho().getItens().clear();

        clienteRepository.saveAndFlush(pedido.getCliente());

        return null;
    }

    @Override
    public GraficoDto findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType) {
        return null;
    }

    private Pedido preencherPedido(Pedido pedido, BindingResult result) {
        Status status = statusRepository.findByStatus("EM PROCESSAMENTO");

        pedido.setStatus(status);
        pedido.setItens(pedido.getCliente().getCarrinho().getItens());

        pedido.getMetodosPagamento().forEach(p -> p.setCartaoCredito(cartaoRepository.findById(p.getCartaoCredito().getId()).get()));

        if(pedido.getCupom() != null){
            pedido.setTotal(BigDecimal.valueOf(pedido.getTotal() - pedido.getCupom().getValor())
                    .setScale(2, RoundingMode.FLOOR)
                    .doubleValue());
        }

        Double total = pedido.getMetodosPagamento().stream().mapToDouble(metodoPagamento -> metodoPagamento.getValorPagamento()).sum();

        if(!total.equals(pedido.getTotal() + 15)){
            result.addError(new ObjectError("pedido", "Valor total e do pagamento são diferentes: TOTAL PAGAMENTO:" +   total + " TOTAL PEDIDO: " + pedido.getTotal()));
            return pedido;
        }

        pedido.getItens().forEach(j -> j.getJogo().setQuantidade(j.getJogo().getQuantidade() - j.getQuantidade()));

        if(pedido.getCupom() != null){
            pedido.getCupom().setQuantidade(pedido.getCupom().getQuantidade() - 1);
        }

        return pedido;
    }
}

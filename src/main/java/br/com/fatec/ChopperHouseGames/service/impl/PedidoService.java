package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Pedido;
import br.com.fatec.ChopperHouseGames.domain.Status;
import br.com.fatec.ChopperHouseGames.dto.GraficoDto;
import br.com.fatec.ChopperHouseGames.repository.*;
import br.com.fatec.ChopperHouseGames.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

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

    @Autowired
    CupomRepository cupomRepository;

    @Override
    public Pedido buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Pedido salvar(Pedido pedido, BindingResult result) {
        System.out.println("--- entrei pra salvar pedido");
        pedido = preencherPedido(pedido, result);
        for(Pedido ped : repository.findAll()){
            if(pedido.getId().equals(ped.getId())){
                pedido.setId(pedido.getId()+1);
                break;
            }
        }
        if(result.hasErrors()){
            return pedido;
        }

        pedido = repository.saveAndFlush(pedido);

        pedido.getCliente().getCarrinho().getItens().clear();

        clienteRepository.saveAndFlush(pedido.getCliente());
        System.out.println("--- salvei pedido " + pedido.getId());
        return pedido;
    }

    @Override
    public List<Pedido> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Pedido editar(Pedido pedido) {
        return repository.saveAndFlush(pedido);
    }

    @Override
    public GraficoDto findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType) {
        return null;
    }

    @Override
    public List<Pedido> buscarByStatus(String status) {
        return repository.findAllByStatus_Status(status);
    }

    private Pedido preencherPedido(Pedido pedido, BindingResult result) {

        pedido.setStatus(statusRepository.findByStatus("EM PROCESSAMENTO"));
        pedido.setItens(pedido.getCliente().getCarrinho().getItens());

        pedido.getMetodosPagamento().forEach(p -> p.setCartaoCredito(cartaoRepository.findById(p.getCartaoCredito().getId()).get()));

        if(pedido.getCupom() != null && pedido.getCupom().getId() != null){
            pedido.setCupom(cupomRepository.findById(pedido.getCupom().getId()).get());

            pedido.setTotal(BigDecimal.valueOf(pedido.getTotal() - pedido.getCupom().getValor())
                    .setScale(2, RoundingMode.FLOOR)
                    .doubleValue());
        }

        if(pedido.getCuponsTroca() != null && !pedido.getCuponsTroca().isEmpty()){
            pedido.getCuponsTroca().forEach(
                    c -> pedido.setTotal(
                            BigDecimal.valueOf(pedido.getTotal() - c.getValor())
                                .setScale(2, RoundingMode.FLOOR)
                                .doubleValue()));
        }

        Double total = pedido.getMetodosPagamento().stream().mapToDouble(metodoPagamento -> metodoPagamento.getValorPagamento()).sum();

        Double pag = pedido.getTotal() + 15;

        pedido.setTotal(pag);

        if(!total.equals(pedido.getTotal())){
            result.addError(new ObjectError("pedido", "Valor total e do pagamento são diferentes: TOTAL PAGAMENTO:" +   total + " TOTAL PEDIDO: " + pedido.getTotal()));
            return pedido;
        }

        pedido.getItens().forEach(j -> j.getJogo().setQuantidade(j.getJogo().getQuantidade() - j.getQuantidade()));

        //diminuindo a quantidade do cupom de desconto quando usado
        if(pedido.getCupom() != null && pedido.getCupom().getId() != null){
            if(!pedido.getCupom().getTipoCupom().getNome().equals("ZERADO")){
                pedido.getCupom().setQuantidade(pedido.getCupom().getQuantidade() - 1);
            }
        }

        //diminuindo a quantidade do cupom de troca quando usado
        if(pedido.getCuponsTroca() != null && !pedido.getCuponsTroca().isEmpty()){
            pedido.getCuponsTroca().forEach(c -> c.setQuantidade(c.getQuantidade() - 1));
        }

        Pedido ped = new Pedido();
        pedido.setId(pedido.getId());

        return pedido;
    }
}

package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.*;
import br.com.fatec.chopperhousegames.core.domain.service.CartaoService;
import br.com.fatec.chopperhousegames.core.domain.service.CupomService;
import br.com.fatec.chopperhousegames.core.domain.service.PedidoService;
import br.com.fatec.chopperhousegames.core.repository.ClienteRepository;
import br.com.fatec.chopperhousegames.core.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    private final ClienteRepository clienteRepository;

    private final CartaoService cartaoService;
    private final CupomService cupomService;

    public PedidoServiceImpl(PedidoRepository repository, ClienteRepository clienteRepository, CartaoService cartaoService, CupomService cupomService) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.cartaoService = cartaoService;
        this.cupomService = cupomService;
    }


    @Override
    public Pedido buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Pedido salvar(Pedido pedido, BindingResult result) {
        pedido = preencherPedido(pedido, result);
        if (result.hasErrors()) {
            return pedido;
        }

        pedido = repository.saveAndFlush(pedido);

        pedido.getCliente().getCarrinho().getItens().clear();

        clienteRepository.saveAndFlush(pedido.getCliente());
        return pedido;
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        return repository.findAll();
    }

    @Override
    public Pedido editar(Pedido pedido) {
        return repository.saveAndFlush(pedido);
    }

    @Override
    public List<Pedido> buscarPedidosEntre(LocalDate dataInicial, LocalDate dataFinal) {
        return repository.buscarPedidosEntre(dataInicial, dataFinal);
    }

    //TODO: Refatorar
    private Pedido preencherPedido(Pedido pedido, BindingResult result) {

        pedido.setStatus(Status.EM_PROCESSAMENTO);
        pedido.setItens(pedido.getCliente().getCarrinho().getItens());

        pedido.getMetodosPagamento().forEach(p -> p.setCartaoCredito(cartaoService.buscarCartaoPorId(p.getCartaoCredito().getId())));
        pedido.setTotal((pedido.getCliente().getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum()));
        if (pedido.getCupom() != null && pedido.getCupom().getId() != null) {
            pedido.setCupom(cupomService.buscarCupomPorId(pedido.getCupom().getId()).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o cupom")));

            pedido.setTotal(BigDecimal.valueOf(pedido.getTotal() - pedido.getCupom().getValor())
                    .setScale(2, RoundingMode.FLOOR)
                    .doubleValue());
        }

        if (pedido.getCuponsTroca() != null && !pedido.getCuponsTroca().isEmpty()) {
            pedido.getCuponsTroca().forEach(
                    c -> pedido.setTotal(
                            BigDecimal.valueOf(pedido.getTotal() - c.getValor())
                                    .setScale(2, RoundingMode.FLOOR)
                                    .doubleValue()));
        }

        Double total = pedido.getMetodosPagamento().stream().mapToDouble(MetodoPagamento::getValorPagamento).sum();

        Double pag = pedido.getTotal() + 15;

        pedido.setTotal(pag);

        if (!total.equals(pedido.getTotal())) {
            result.addError(new ObjectError("pedido", "Valor total e do pagamento são diferentes: TOTAL PAGAMENTO:" + total + " TOTAL PEDIDO: " + pedido.getTotal()));
            return pedido;
        }

        if (!pedido.getCuponsTroca().isEmpty()) {
            Double totalCupom = pedido.getCuponsTroca().stream().mapToDouble(Cupom::getValor).sum();
            if (totalCupom > pedido.getTotal()) {
                Cupom cupom = new Cupom();

                Random random = new Random();

                String codigo = random.ints(48, 122 + 1)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(10)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                cupom.setCodigo(codigo);
                cupom.setQuantidade(1);
                cupom.setCliente(pedido.getCliente());
                cupom.setTipoCupom(TipoCupom.TROCA);
                cupom.setValor(totalCupom - pedido.getTotal());
            }
        }

        pedido.getItens().forEach(j -> j.getJogo().setQuantidade(j.getJogo().getQuantidade() - j.getQuantidade()));

        //diminuindo a quantidade do cupom de desconto quando usado
        if (pedido.getCupom() != null && pedido.getCupom().getId() != null) {
            if (!pedido.getCupom().getTipoCupom().equals(TipoCupom.ZERADO)) {
                pedido.getCupom().setQuantidade(pedido.getCupom().getQuantidade() - 1);
            }
        }

        //diminuindo a quantidade do cupom de troca quando usado
        if (pedido.getCuponsTroca() != null && !pedido.getCuponsTroca().isEmpty()) {
            pedido.getCuponsTroca().forEach(c -> c.setQuantidade(c.getQuantidade() - 1));
        }

        return pedido;
    }

}

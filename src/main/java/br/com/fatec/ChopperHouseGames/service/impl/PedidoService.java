package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.*;
import br.com.fatec.ChopperHouseGames.dto.ChartDto;
import br.com.fatec.ChopperHouseGames.dto.DataSetDto;
import br.com.fatec.ChopperHouseGames.dto.GraficoDto;
import br.com.fatec.ChopperHouseGames.repository.*;
import br.com.fatec.ChopperHouseGames.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    GeneroRepository generoRepository;


    @Override
    public Pedido buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Pedido salvar(Pedido pedido, BindingResult result) {
        System.out.println("--- entrei pra salvar pedido");
        pedido = preencherPedido(pedido, result);
        if(result.hasErrors()){
            return pedido;
        }

        //pedido = criaNovoPedido(pedido);

        pedido = repository.saveAndFlush(pedido);

        pedido.getCliente().getCarrinho().getItens().clear();

        clienteRepository.saveAndFlush(pedido.getCliente());
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

    @Override
    public ChartDto buscarTodosCriadosEntre(Date dataInicial, Date dataFinal, Integer tipoBusca) {
        if (null == tipoBusca){
            tipoBusca = 0;
        }

        List<Pedido> pedidosFiltrados = repository.findAllByDataCriacaoBetweenOrderByDataCriacao(dataInicial, dataFinal);

        Map<LocalDate, List<Pedido>> agrupadoPorData = new HashMap<>();

        LocalDate localDate = null;

        for(Pedido ped : pedidosFiltrados){
            Date data = new Date(ped.getDataCriacao().getTime());
            localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(!agrupadoPorData.containsKey(localDate.getDayOfYear())){
                agrupadoPorData.put(localDate, new ArrayList<>());
                agrupadoPorData.get(localDate).add(ped);
            }else {
                agrupadoPorData.get(localDate).add(ped);
            }
        }

        List<HashMap<String, Double>> pedidoValor = new ArrayList<>();

        ChartDto chartDTO = new ChartDto();
        List<DataSetDto> listaDataSet = new ArrayList<>();

        if (tipoBusca.equals(0)) {
            List<Jogo> jogos = jogoRepository.findAll();

            for (Jogo jogo : jogos) {
                DataSetDto dataSetDTO = new DataSetDto();
                List<Double> doubleList = new ArrayList<>();
                dataSetDTO.setLabel(jogo.getTitulo());


                for (List<Pedido> order : agrupadoPorData.values()) {
                    Integer amount = 0;
                    for (Pedido orderValueGroup : order) {
                        for (Item item : orderValueGroup.getItens()) {
                            if (item.getJogo().equals(jogo))
                                amount++;
                        }
                    }
                    doubleList.add(amount * jogo.getPreco());
                }

                dataSetDTO.setData(doubleList);
                listaDataSet.add(dataSetDTO);
            }
        } else {
            List<Genero> generos = generoRepository.findAll();

            for (Genero genero : generos) {
                DataSetDto dataSetDTO = new DataSetDto();
                List<Double> doubleList = new ArrayList<>();
                dataSetDTO.setLabel(genero.getNome());


                for (List<Pedido> pedidos : agrupadoPorData.values()) {
                    Integer amount = 0;
                    Jogo jogo = null;
                    for (Pedido orderValueGroup : pedidos) {
                        for (Item item : orderValueGroup.getItens()) {
                            if (item.getJogo().getGeneros().contains(genero)) {
                                amount++;
                            }
                            jogo = item.getJogo();
                        }
                    }
                    if (null != jogo)
                        doubleList.add(amount * jogo.getPreco());
                }

                dataSetDTO.setData(doubleList);
                listaDataSet.add(dataSetDTO);
            }
        }

        chartDTO.setLabel(agrupadoPorData.keySet().stream().map(lDate -> lDate.toString()).collect(Collectors.toList()));
        chartDTO.setDatasets(listaDataSet);

        return chartDTO;
    }

    @Override
    public List<HashMap<String, Double>> preencherIndexCards() {
        List<HashMap<String, Double>> cards = new ArrayList<>();

        List<Pedido> allOrders = repository.findAll();

        HashMap<String, Double> totalOrder = new HashMap<>();
        HashMap<String, Double> cardSingle = new HashMap<>();
        HashMap<String, Double> cardMultiple = new HashMap<>();
        Double totalSingleCreditCard = 0d;
        Double totalMultipleCreditCard = 0d;

        totalOrder.put("total das vendas", allOrders.stream().mapToDouble(o -> o.getTotal()).sum());

        for (Pedido order : allOrders) {
            if(order.getMetodosPagamento().size() > 1){
                totalMultipleCreditCard += 1;
            } else {
                totalSingleCreditCard += 1;
            }
        }

        cardSingle.put("Vendas (1 cartão de crédito)", totalSingleCreditCard);
        cardMultiple.put("Vendas (vários cartões de crédito)", totalMultipleCreditCard);

        cards.add(totalOrder);
        cards.add(cardSingle);
        cards.add(cardMultiple);

        return cards;
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

        return pedido;
    }
}

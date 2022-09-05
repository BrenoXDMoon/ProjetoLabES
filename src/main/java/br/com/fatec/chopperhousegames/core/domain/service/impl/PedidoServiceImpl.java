package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.*;
import br.com.fatec.chopperhousegames.core.domain.service.PedidoService;
import br.com.fatec.chopperhousegames.core.repository.*;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ChartDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.DataSetDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.GraficoDTO;
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
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    private final StatusRepository statusRepository;

    private final ClienteRepository clienteRepository;

    private final JogoRepository jogoRepository;

    private final GeneroRepository generoRepository;
    private final TipoCupomRepository tipoCupomRepository;

    public PedidoServiceImpl(PedidoRepository repository, StatusRepository statusRepository, ClienteRepository clienteRepository, JogoRepository jogoRepository, GeneroRepository generoRepository, TipoCupomRepository tipoCupomRepository) {
        this.repository = repository;
        this.statusRepository = statusRepository;
        this.clienteRepository = clienteRepository;
        this.jogoRepository = jogoRepository;
        this.generoRepository = generoRepository;
        this.tipoCupomRepository = tipoCupomRepository;
    }


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
    public GraficoDTO findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType) {
        return null;
    }

    @Override
    public List<Pedido> buscarByStatus(String status) {
        return repository.findAllByStatus_Status(status);
    }

    @Override
    public ChartDTO buscarTodosCriadosEntre(Date dataInicial, Date dataFinal, Integer tipoBusca) {
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

        ChartDTO chartDTO = new ChartDTO();
        List<DataSetDTO> listaDataSet = new ArrayList<>();

        //filtro de jogo
        if (tipoBusca.equals(0)) {
            List<Jogo> jogos = jogoRepository.findAll();

            for (Jogo jogo : jogos) {
                DataSetDTO dataSetDTO = new DataSetDTO();
                List<Double> doubleList = new ArrayList<>();
                dataSetDTO.setLabel(jogo.getTitulo());


                for (List<Pedido> order : agrupadoPorData.values()) {
                    Integer amount = 0;
                    for (Pedido orderValueGroup : order) {
                        for (Item item : orderValueGroup.getItens()) {
                            if (item.getJogo().equals(jogo))
                                amount += item.getQuantidade();
                        }
                    }
                    doubleList.add(Double.parseDouble(String.valueOf(amount)));
                }

                dataSetDTO.setData(doubleList);
                listaDataSet.add(dataSetDTO);
            }
        } else {//filtro de genero de jogo
            List<Genero> generos = generoRepository.findAll();
            for (Genero genero : generos) {
                DataSetDTO dataSetDTO = new DataSetDTO();
                List<Double> doubleList = new ArrayList<>();
                dataSetDTO.setLabel(genero.getNome());

                for (List<Pedido> pedidos : agrupadoPorData.values()) {
                    Integer amount = 0;
                    Jogo jogo = null;
                    for (Pedido orderValueGroup : pedidos) {
                        for (Item item : orderValueGroup.getItens()) {
                            if (item.getJogo().getGeneros().contains(genero)) {
                                amount += item.getQuantidade();
                            }
                            jogo = item.getJogo();
                        }
                    }

                    if (null != jogo)
                        doubleList.add(Double.parseDouble(String.valueOf(amount)));
                }

                dataSetDTO.setData(doubleList);
                listaDataSet.add(dataSetDTO);
            }
        }

        chartDTO.setLabel(agrupadoPorData.keySet().stream().map(LocalDate::toString).toList());
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

        totalOrder.put("total das vendas", allOrders.stream().mapToDouble(Pedido::getTotal).sum());

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

        //TODO: ALTERAR PARAMETROS DE ONDE SOLICITA ID PARA LONG
//        pedido.getMetodosPagamento().forEach(p -> p.setCartaoCredito(cartaoRepository.findById(p.getCartaoCredito().getId()).get()));
        pedido.setTotal((pedido.getCliente().getCarrinho().getItens().stream().mapToDouble(i -> i.getJogo().getPreco() * i.getQuantidade().doubleValue()).sum()));
        if(pedido.getCupom() != null && pedido.getCupom().getId() != null){
            //TODO: ALTERAR PARAMETROS DE ONDE SOLICITA ID PARA LONG
//            pedido.setCupom(cupomRepository.findById(pedido.getCupom().getId()).get());

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

        if(!pedido.getCuponsTroca().isEmpty()){
            Double totalCupom = pedido.getCuponsTroca().stream().mapToDouble(Cupom::getValor).sum();
            if(totalCupom > pedido.getTotal()){
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
                cupom.setTipoCupom(tipoCupomRepository.findByNome("TROCA"));
                cupom.setValor(totalCupom - pedido.getTotal());
            }
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

        //TODO: ALTERAR PARAMETROS DE ONDE SOLICITA ID PARA LONG
        //pedido.setId(geraIdNovo(pedido));

        return pedido;
    }

    private Integer geraIdNovo(Pedido pedido) {
        Integer id = null;
        id = repository.findAll().size() + 1;
        return id;
    }
}

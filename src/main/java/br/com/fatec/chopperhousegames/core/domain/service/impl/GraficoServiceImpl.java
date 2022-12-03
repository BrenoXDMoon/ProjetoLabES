package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.*;
import br.com.fatec.chopperhousegames.core.domain.service.GeneroService;
import br.com.fatec.chopperhousegames.core.domain.service.GraficoService;
import br.com.fatec.chopperhousegames.core.domain.service.JogoService;
import br.com.fatec.chopperhousegames.core.domain.service.PedidoService;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ChartDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.DataSetDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GraficoServiceImpl implements GraficoService {

    private final PedidoService pedidoService;
    private final GeneroService generoService;

    private final JogoService jogoService;

    public GraficoServiceImpl(PedidoService pedidoService, GeneroService generoService, JogoService jogoService) {
        this.pedidoService = pedidoService;
        this.generoService = generoService;
        this.jogoService = jogoService;
    }

    @Override
    public ChartDTO montarGraficoComValoresCriadosEntre(LocalDate dataInicial, LocalDate dataFinal, Integer tipoBusca) {
        if (null == tipoBusca){
            tipoBusca = 0;
        }

        List<Pedido> pedidosFiltrados = pedidoService.buscarPedidosEntre(dataInicial, dataFinal);

        Map<LocalDate, List<Pedido>> agrupadoPorData = new HashMap<>();

        for(Pedido ped : pedidosFiltrados){
            LocalDate localDate = ped.getDataCriacao();
            //TODO: Verificar se é necessário criar um novo objeto LocalDate
            if(!agrupadoPorData.containsKey(localDate.getDayOfYear())){
                agrupadoPorData.put(localDate, new ArrayList<>());
                agrupadoPorData.get(localDate).add(ped);
            }else {
                agrupadoPorData.get(localDate).add(ped);
            }
        }

        ChartDTO chartDTO = new ChartDTO();
        List<DataSetDTO> listaDataSet = new ArrayList<>();

        //filtro de jogo
        if (tipoBusca.equals(0)) {
            List<Jogo> jogos = jogoService.listarTodosJogos();

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
            List<Genero> generos = generoService.buscarTodosGeneros();
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

        List<Pedido> allOrders = pedidoService.buscarTodosPedidos();

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
}

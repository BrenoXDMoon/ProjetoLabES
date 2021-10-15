package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.Pedido;
import br.com.fatec.ChopperHouseGames.dto.GraficoDto;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;

public interface IPedidoService {

    Pedido buscarById(Integer id);
    Pedido salvar(Pedido pedido, BindingResult result);
    List<Pedido> buscarTodos();
    Pedido editar(Pedido pedido);
    GraficoDto findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType);
    List<Pedido> buscarByStatus(String status);
}

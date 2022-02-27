package br.com.fatec.ChopperHouseGames.inbound.facade;

import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;

import java.util.Optional;

public interface ClienteFacade extends Facade {


    Optional<ClienteDTO> buscarPorEmail(String email);
}

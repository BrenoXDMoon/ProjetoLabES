package br.com.fatec.ChopperHouseGames.inbound.validator;

import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import org.springframework.validation.BindingResult;

public interface ClienteValidator {

    BindingResult validaFormularioCadastro(ClienteDTO dto, BindingResult result);
}

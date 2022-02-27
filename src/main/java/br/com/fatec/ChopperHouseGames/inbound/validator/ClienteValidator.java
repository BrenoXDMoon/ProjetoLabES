package br.com.fatec.ChopperHouseGames.inbound.validator;

import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.SenhaDTO;
import org.springframework.validation.BindingResult;

public interface ClienteValidator {

    BindingResult validaFormularioCadastro(ClienteDTO dto, BindingResult result);
    BindingResult validaAlteracaoSenha(SenhaDTO dto, ClienteDTO clienteDTO, BindingResult result);
    Boolean validaRoleUsuario(ClienteDTO dto);
}

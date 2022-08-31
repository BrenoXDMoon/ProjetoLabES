package br.com.fatec.chopperhousegames.inbound.validator;

import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.SenhaDTO;
import org.springframework.validation.BindingResult;

public interface ClienteValidator {

    BindingResult validaFormularioCadastro(ClienteDTO dto, BindingResult result);
    BindingResult validaAlteracaoSenha(SenhaDTO dto, ClienteDTO clienteDTO, BindingResult result);
    Boolean validaRoleUsuario(ClienteDTO dto);
}

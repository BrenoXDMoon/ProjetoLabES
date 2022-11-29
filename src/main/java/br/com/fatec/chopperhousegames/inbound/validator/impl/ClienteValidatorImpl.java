package br.com.fatec.chopperhousegames.inbound.validator.impl;

import br.com.fatec.chopperhousegames.inbound.facade.ClienteFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.SenhaDTO;
import br.com.fatec.chopperhousegames.inbound.validator.ClienteValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.regex.Pattern;

@Component
public class ClienteValidatorImpl implements ClienteValidator {

    ClienteFacade facade;

    String resultado = "resultado";

    @Override
    public BindingResult validaFormularioCadastro(ClienteDTO dto, BindingResult result) {

        if (confirmaSenha(dto.getSenha(), dto.getConfirmaSenha())) {
            result.addError(new ObjectError(resultado, "Senha não confere com a confirmação de senha"));
        }
        if (validaSenha(dto.getSenha())) {
            result.addError(new ObjectError(resultado, "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }
        if (!validaEmail(dto.getEmail())) {
            result.addError(new ObjectError(resultado, "Email já cadastrado"));
        }

        return result;
    }

    @Override
    public BindingResult validaAlteracaoSenha(SenhaDTO dto, ClienteDTO clienteDTO, BindingResult result) {

        if (!senhaAntigaCorreta(clienteDTO, dto)) {
            result.addError(new ObjectError(resultado, "A senha antiga não confere"));
        }

        if (confirmaSenha(dto.getSenha(), dto.getConfirmaSenha())) {
            result.addError(new ObjectError(resultado, "Senha não confere com a confirmação de senha"));
        }
        if (validaSenha(dto.getSenha())) {
            result.addError(new ObjectError(resultado, "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }

        return null;
    }

    public boolean senhaAntigaCorreta(ClienteDTO cliente, SenhaDTO dto) {
        return new BCryptPasswordEncoder().matches(dto.getSenhaAntiga(), cliente.getSenha());
    }


    @Override
    public Boolean validaRoleUsuario(ClienteDTO dto) {
        return dto.getRoles().equals("CLIENTE");
    }

    public boolean validaSenha(String senha) {
        return Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$").matcher(senha).matches();

    }

    //Se a senha for igual a confirmação de senha retorna false
    public boolean confirmaSenha(String senha, String confirmaSenha) {
        return !senha.equals(confirmaSenha);
    }

    public boolean validaEmail(String email) {
        return facade.buscarPorEmail(email).isEmpty();
    }

}

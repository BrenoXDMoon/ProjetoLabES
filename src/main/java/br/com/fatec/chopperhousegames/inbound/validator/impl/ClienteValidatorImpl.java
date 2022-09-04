package br.com.fatec.chopperhousegames.inbound.validator.impl;

import br.com.fatec.chopperhousegames.inbound.facade.ClienteFacade;
import br.com.fatec.chopperhousegames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.chopperhousegames.inbound.facade.dto.SenhaDTO;
import br.com.fatec.chopperhousegames.inbound.validator.ClienteValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ClienteValidatorImpl implements ClienteValidator {

    ClienteFacade facade;

    @Override
    public BindingResult validaFormularioCadastro(ClienteDTO dto, BindingResult result) {

        if(!confirmaSenha(dto.getSenha(), dto.getConfirmaSenha())){
            result.addError(new ObjectError("resultado", "Senha não confere com a confirmação de senha"));
        }
        if(!validaSenha(dto.getSenha())){
            result.addError(new ObjectError("resultado", "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }
        if(!validaEmail(dto.getEmail())){
            result.addError(new ObjectError("resultado", "Email já cadastrado"));
        }

        return result;
    }

    @Override
    public BindingResult validaAlteracaoSenha(SenhaDTO dto, ClienteDTO clienteDTO, BindingResult result) {

        if(!senhaAntigaCorreta(clienteDTO, dto)){
            result.addError(new ObjectError("resultado","A senha antiga não confere"));
        }

        if(!confirmaSenha(dto.getSenha(), dto.getConfirmaSenha())){
            result.addError(new ObjectError("resultado", "Senha não confere com a confirmação de senha"));
        }
        if(!validaSenha(dto.getSenha())){
            result.addError(new ObjectError("resultado", "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }

        return null;
    }

    public boolean senhaAntigaCorreta(ClienteDTO cliente, SenhaDTO dto){

        if(new BCryptPasswordEncoder().matches(dto.getSenhaAntiga(), cliente.getSenha())){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public Boolean validaRoleUsuario(ClienteDTO dto) {
        return dto.getRoles().equals("CLIENTE");
    }

    public boolean validaSenha(String senha) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }

    public boolean confirmaSenha(String senha, String confirmaSenha) {
        if(senha.equals(confirmaSenha)){
            return true;
        }
        return false;
    }

    public boolean validaEmail(String email) {
        ;

        if(facade.buscarPorEmail(email).isPresent()){
            return false;
        }else{
            return true;
        }
    }

}

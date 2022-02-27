package br.com.fatec.ChopperHouseGames.inbound.validator.impl;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.inbound.facade.ClienteFacade;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.validator.ClienteValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ClienteValidatorImpl implements ClienteValidator {

    ClienteFacade facade;

    @Override
    public BindingResult validaFormularioCadastro(ClienteDTO dto, BindingResult result) {

        if(!confirmaSenha(dto)){
            result.addError(new ObjectError("resultado", "Senha não confere com a confirmação de senha"));
        }
        if(!validaSenha(dto)){
            result.addError(new ObjectError("resultado", "A senha deve conter ao menos numero," +
                    " letra maiuscula, letra minuscula, caracter especial e a quantidade entre 8 e 20"));
        }
        if(!validaEmail(dto.getEmail())){
            result.addError(new ObjectError("resultado", "Email já cadastrado"));
        }

        return result;
    }

    @Override
    public Boolean validaRoleUsuario(ClienteDTO dto) {
        return dto.getRoles().equals("CLIENTE");
    }

    public boolean validaSenha(ClienteDTO dto) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
        Matcher matcher = pattern.matcher(dto.getSenha());
        return matcher.matches();
    }

    public boolean confirmaSenha(ClienteDTO dto) {
        if(dto.getSenha().equals(dto.getConfirmaSenha())){
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

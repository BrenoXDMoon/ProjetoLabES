package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Data
public class SenhaDTO {

    @NotEmpty(message = "Senha não pode estar em branco")
    public String senha;

    @NotEmpty(message = "A confirmação da senha não pode estar em branco")
    public String confirmaSenha;

    @NotEmpty(message = "A senha antiga não pode estar em branco")
    public String senhaAntiga;

    public boolean confirmaSenha() {
        System.out.println(this.senha.equals(confirmaSenha));
        if(this.senha.equals(confirmaSenha)){
            return true;
        }else{
            return false;
        }
    }



    public String toSenha() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}

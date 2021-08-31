package br.com.fatec.ChopperHouseGames.dto;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Data
public class SenhaDto {

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

    public boolean senhaAntigaCorreta(Cliente cliente){

        if(new BCryptPasswordEncoder().matches(this.senhaAntiga, cliente.getSenha())){
            return true;
        }else{
            return false;
        }
    }
}

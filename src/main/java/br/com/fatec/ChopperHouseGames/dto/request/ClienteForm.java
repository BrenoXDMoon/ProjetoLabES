package br.com.fatec.ChopperHouseGames.dto.request;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class ClienteForm {

    @NotNull(message = "Nome Completo é obrigatório")
    @NotBlank(message = "Nome Completo não pode estar em branco")
    private String nomeCompleto;

    @NotNull(message = "Email é obrigatório")
    @NotBlank(message = "Email não pode estar em branco")
    private String email;

    @NotNull(message = "Data de Nascimento é obrigatório")
    @NotBlank(message = "Data de Nascimento não pode estar em branco")
    private String dataNascimento;

    @NotNull(message = "Telefone é obrigatório")
    @NotBlank(message = "Telefone não pode estar em branco")
    private String telefone;


    @Length(min = 6)
    private String senhaCriptografada;

    @NotNull(message = "Senha é obrigatório")
    @NotBlank(message = "Senha não pode estar em branco")
    @Transient
    private String senha;

    public boolean confirmaSenha() {
        if(null == this.senhaCriptografada)
            if(null == this.senha || this.senha.length() <= 0)
                return false;
        return true;
    }

    public boolean validaSenha() {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }

    public Cliente toCliente(){
        Cliente cliente = new Cliente();

        cliente.setNomeCompleto(this.nomeCompleto);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setAtivo(true);
        cliente.setEmail(this.email);
        cliente.setSenhaCriptografada(this.senhaCriptografada);
        cliente.setSenha(this.senha);
        cliente.setTelefone(this.telefone);


        return cliente;
    }
}

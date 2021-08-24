package br.com.fatec.ChopperHouseGames.dto.request;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.domain.TipoCliente;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class ClienteDtoForm {

    private Integer id;

    @NotNull
    @NotEmpty(message = "Nome Completo é obrigatório!")
    private String nomeCompleto;

    @NotNull
    @NotEmpty(message = "Email é obrigatório!")
    private String email;

    @NotNull
    @NotEmpty(message = "Data de Nascimento é obrigatório!")
    private String dataNascimento;

    @NotNull
    @NotEmpty(message = "Telefone é obrigatório!")
    private String telefone;

    @NotNull
    @NotEmpty(message = "CPF é obrigatório!")
    @CPF(message = "CPF Inválido!")
    private String cpf;

    @NotNull
    @NotEmpty(message = "Senha é obrigatória!")
    private String senha;

    @NotNull
    @NotEmpty(message = "Confirmar a senha é obrigatório!")
    private String confirmaSenha;

    private TipoCliente tipoCliente;

    public boolean confirmaSenha() {
        if(null == this.confirmaSenha)
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
        if(this.id != null){
            cliente.setId(this.id);
        }
        cliente.setNomeCompleto(this.nomeCompleto);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setAtivo(true);
        cliente.setEmail(this.email);
        cliente.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        cliente.setTelefone(this.telefone);
        return cliente;
    }

    public Cliente toClienteEdit(){

        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNomeCompleto(this.nomeCompleto);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setSenha(this.senha);
        cliente.setAtivo(true);
        cliente.setCpf(this.cpf);
        cliente.setEmail(this.email);
        cliente.setTelefone(this.telefone);
        cliente.setTipoCliente(this.tipoCliente);
        cliente.setRoles("CLIENTE");
        return cliente;
    }

}

package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.TipoCliente;
import br.com.fatec.ChopperHouseGames.core.repository.ClienteRepository;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class ClienteDto {

    private Integer id;

    @NotEmpty(message = "Nome Completo é obrigatório!")
    private String nomeCompleto;

    @NotEmpty(message = "Email é obrigatório!")
    private String email;

    @NotEmpty(message = "Data de Nascimento é obrigatório!")
    private String dataNascimento;

    @NotEmpty(message = "Telefone é obrigatório!")
    private String telefone;

    @NotEmpty(message = "CPF é obrigatório!")
    @CPF(message = "CPF Inválido!")
    private String cpf;

    @NotEmpty(message = "Senha é obrigatória!")
    private String senha;

    @NotEmpty(message = "Confirmar a senha é obrigatório!")
    private String confirmaSenha;

    private TipoCliente tipoCliente;

    public boolean confirmaSenha() {
       if(this.senha.equals(this.confirmaSenha)){
           return true;
       }
        return false;
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
        cliente.setCpf(this.cpf);
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

        System.out.println("-" + this.senha);
        System.out.println("-" + this.confirmaSenha);
        cliente.setSenha(new BCryptPasswordEncoder().encode(this.senha));

        cliente.setAtivo(true);
        cliente.setCpf(this.cpf);
        cliente.setEmail(this.email);
        cliente.setTelefone(this.telefone);
        cliente.setTipoCliente(this.tipoCliente);
        cliente.setRoles("CLIENTE");
        return cliente;
    }

    public boolean validaEmail(ClienteRepository clienteRepository, String email) {
        Optional<Cliente> optional = clienteRepository.findByEmail(email);

        if(optional.isPresent()){
            return false;
        }else{
            return true;
        }
    }
}

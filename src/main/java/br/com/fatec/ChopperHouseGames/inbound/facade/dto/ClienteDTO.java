package br.com.fatec.ChopperHouseGames.inbound.facade.dto;

import br.com.fatec.ChopperHouseGames.core.domain.TipoCliente;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ClienteDTO extends EntidadeDTO {

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

    private String roles;

    private Boolean ativo;

}

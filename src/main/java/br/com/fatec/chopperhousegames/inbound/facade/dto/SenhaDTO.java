package br.com.fatec.chopperhousegames.inbound.facade.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SenhaDTO {

    @NotEmpty(message = "Senha não pode estar em branco")
    public String senha;

    @NotEmpty(message = "A confirmação da senha não pode estar em branco")
    public String confirmaSenha;

    @NotEmpty(message = "A senha antiga não pode estar em branco")
    public String senhaAntiga;

    public boolean confirmarSenha() {
        return this.senha.equals(confirmaSenha);
    }
}

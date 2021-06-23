package br.com.fatec.chopperhouse.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Cliente extends EntidadeDominio{

    private String nome;
    private String email;
    private Calendar dataNascimento;
    private TIPO_CLIENTE tipoCliente;
    private GENERO genero;
    private Senha senha;
    private boolean ativo;
    private List<Endereco> enderecos;
    private List<Documento> documentos;
    private List<CartaoCredito> cartoes;
}

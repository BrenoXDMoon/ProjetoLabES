package br.com.fatec.chopperhouse.dto.request;


import br.com.fatec.chopperhouse.models.Genero;
import br.com.fatec.chopperhouse.models.Senha;
import br.com.fatec.chopperhouse.models.TipoCliente;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private Integer id;
    private String nomeCompleto;
    private String email;
    private String dataNascimento;
    private Genero genero;
    private DocumentoDTO documento;
    private EnderecoDTO endereco;
    private Senha senha;
    private TipoCliente tipoCliente;
    private boolean ativo;


}

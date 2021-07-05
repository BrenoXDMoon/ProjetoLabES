package br.com.fatec.chopperhouse.models.mapper;


import br.com.fatec.chopperhouse.dto.request.ClienteDTO;
import br.com.fatec.chopperhouse.models.Cliente;
import br.com.fatec.chopperhouse.models.Documento;
import br.com.fatec.chopperhouse.models.Endereco;

import java.util.ArrayList;
import java.util.List;

public interface ClienteMapper {

    static Cliente toCliente(ClienteDTO cli){
        List<Documento> documentos = new ArrayList<Documento>();
        Documento documento = new Documento(cli.getDocumento().getId(), cli.getDocumento().getTipoDocumento(), cli.getDocumento().getCodigo(), cli.getDocumento().getValidade());
        documentos.add(documento);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        Endereco endereco = new Endereco(cli.getEndereco().getId(),
                cli.getEndereco().getLogradouro(),
                cli.getEndereco().getNumero(),
                cli.getEndereco().getBairro(),
                cli.getEndereco().getComplemento(),
                cli.getEndereco().getCep(),
                cli.getEndereco().getCidade(),
                cli.getEndereco().getTpEndereco());

        enderecos.add(endereco);

        return new Cliente(cli.getId(), cli.getNomeCompleto(),
                cli.getEmail(), cli.getDataNascimento(),
                cli.getGenero(), documentos, enderecos,
                cli.getSenha(), cli.getTipoCliente(), true);
    }

}

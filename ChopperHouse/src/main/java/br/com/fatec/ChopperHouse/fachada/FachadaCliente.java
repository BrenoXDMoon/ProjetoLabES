package br.com.fatec.chopperhouse.fachada;

import br.com.fatec.chopperhouse.models.Cliente;
import br.com.fatec.chopperhouse.models.EntidadeDominio;
import br.com.fatec.chopperhouse.models.Resultado;
import br.com.fatec.chopperhouse.repository.ClienteRepository;
import br.com.fatec.chopperhouse.repository.DocumentoRepository;
import br.com.fatec.chopperhouse.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FachadaCliente implements IFachada{

    @Autowired
    ClienteRepository repository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public Resultado salvar(EntidadeDominio ent) {

        System.out.println("Entrou na Fachada");
        Cliente cliente = (Cliente) ent;
        documentoRepository.save(cliente.getDocumentos().get(0));
        enderecoRepository.save(cliente.getEnderecos().get(0));
        repository.saveAndFlush(cliente);
        Resultado resultado = new Resultado();
        resultado.setEntidade(cliente);
        return resultado;
    }

    @Override
    public Resultado listar(EntidadeDominio ent) {

        List<Cliente> lista = new ArrayList<Cliente>();
        Resultado resultado = new Resultado();

        lista = repository.findAll();

        for(Cliente c : lista){
            resultado.add(c);
        }

        return resultado;
    }

    @Override
    public Resultado buscarById(EntidadeDominio ent) {

        Cliente cliente = (Cliente) ent;
        cliente = repository.findById(cliente.getId()).get();

        return null;
    }

    @Override
    public Resultado editar(EntidadeDominio ent) {

        Cliente cliente = (Cliente) ent;
        cliente = repository.save(cliente);

        Resultado resultado = new Resultado();

        resultado.setEntidade(cliente);

        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio ent) {
        return null;
    }

    @Override
    public Resultado ativar(EntidadeDominio ent) {
        return null;
    }
}

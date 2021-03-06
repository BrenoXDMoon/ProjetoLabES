package br.com.fatec.ChopperHouseGames.converter;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.domain.EntidadeDominio;

import java.util.ArrayList;
import java.util.List;

public class ConverterCliente {

    public static List<Cliente> converte(List<EntidadeDominio> lista){

        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;

        for(EntidadeDominio ent : lista){
            cliente = (Cliente) ent;
            clientes.add(cliente);
        }

        return clientes;

    }
}

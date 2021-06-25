package br.com.fatec.ChopperHouse.command;

import br.com.fatec.ChopperHouse.model.EntidadeDominio;
import br.com.fatec.ChopperHouse.model.Resultado;

public class ExcluirCommand extends AbstractCommand{

    @Override
    public Resultado executar(EntidadeDominio ent) {
        
        return fachada.excluir(ent);
    }
    
}

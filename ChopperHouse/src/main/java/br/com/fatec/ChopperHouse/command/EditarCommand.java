package br.com.fatec.chopperhouse.command;

import br.com.fatec.chopperhouse.fachada.IFachada;
import br.com.fatec.chopperhouse.models.EntidadeDominio;
import br.com.fatec.chopperhouse.models.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditarCommand implements ICommand{

    @Autowired
    IFachada fachada;

    @Override
    public Resultado executar(EntidadeDominio ent) {
        return fachada.editar(ent);
    }
}
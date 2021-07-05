package br.com.fatec.chopperhouse.command;


import br.com.fatec.chopperhouse.models.EntidadeDominio;
import br.com.fatec.chopperhouse.models.Resultado;
import org.springframework.stereotype.Component;

@Component
public interface ICommand {

    Resultado executar(EntidadeDominio ent);
}

package br.com.fatec.chopperhouse.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resultado {

    private String mensagem;
    private EntidadeDominio entidade;
    private List<EntidadeDominio> entidades;
}

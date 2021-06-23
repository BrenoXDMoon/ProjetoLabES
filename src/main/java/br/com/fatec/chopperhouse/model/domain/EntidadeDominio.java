package br.com.fatec.chopperhouse.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeDominio {
    private Integer id;
    private Calendar dataAtualizacao;
}

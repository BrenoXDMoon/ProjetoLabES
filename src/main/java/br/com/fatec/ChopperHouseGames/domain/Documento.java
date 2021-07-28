package br.com.fatec.ChopperHouseGames.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Documento extends EntidadeDominio {

    @NotBlank(message = "Número do documento não pode ser estar em branco")
    @NotNull(message = "Número do documento não pode ser nulo")
    @Length(min = 12, message = "O Documento é inválido")
    private String codigo;

    @NotNull(message = "Data de expedição não pode ser nulo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date validade;

    @NotNull(message = "Selecione um tipo de documento")
    @ManyToOne
    private TipoDocumento tipoDocumento;
}

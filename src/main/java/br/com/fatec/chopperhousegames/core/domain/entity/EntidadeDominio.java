package br.com.fatec.chopperhousegames.core.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate dataCriacao;

    //TODO: IMPLEMENTAR UM PREPERSIST PARA SETAR A DATA DE CRIAÇÃO
}

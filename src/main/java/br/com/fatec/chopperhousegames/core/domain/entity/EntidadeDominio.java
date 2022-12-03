package br.com.fatec.chopperhousegames.core.domain.entity;

import br.com.fatec.chopperhousegames.core.domain.entity.listener.AuditEntidadeDominioListener;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
@EntityListeners({AuditEntidadeDominioListener.class})
public class EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate dataCriacao;
}

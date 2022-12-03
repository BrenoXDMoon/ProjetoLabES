package br.com.fatec.chopperhousegames.core.domain.entity.listener;

import br.com.fatec.chopperhousegames.core.domain.entity.EntidadeDominio;

import javax.persistence.PrePersist;
import java.time.LocalDate;

public class AuditEntidadeDominioListener {

    @PrePersist
    public void prePersist(EntidadeDominio entidadeDominio) {
        entidadeDominio.setDataCriacao(LocalDate.now());
    }

}

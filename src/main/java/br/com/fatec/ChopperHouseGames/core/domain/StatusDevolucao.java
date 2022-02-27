package br.com.fatec.ChopperHouseGames.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusDevolucao {
    AGUARDANDO_RESPOSTA("Aguardando resposta"),
    EM_PROCESSAMENTO("Em processamento"),
    ACEITO("Aceito"),
    RECUSADO("Recusado"),
    FINALIZADO("Finalizado");

    private final String descricao;
}

package br.com.fatec.ChopperHouseGames.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusDevolucao {
    AGUARDANDO_RESPOSTA("Aguardando resposta"),
    EM_PROCESSAMENTO("Aguardando resposta"),
    ACEITO("Aceito"),
    RECUSADO("Recusado");

    private final String descricao;
}

package br.com.fatec.chopperhousegames.core.domain.entity;

public enum Status {
    EM_PROCESSAMENTO,
    PAGAMENTO_REALIZADO,
    ENVIADO_PARA_TRANSPORTADORA,
    EM_TRANSPORTE,
    ENTREGUE,
    TROCA_SOLICITADA,
    TROCA_APROVADA,
    TROCA_RECUSADA,
    PRODUTO_ENVIADO_PARA_TROCA,
    PRODUTO_RECEBIDO_PARA_TROCA,
    TROCA_REALIZADA,
    CANCELAMENTO_SOLICITADO,
    CANCELAMENTO_APROVADO,
    CANCELAMENTO_RECUSADO;

    public String getStatus() {
        return this.name();
    }
}

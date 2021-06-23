package br.com.fatec.chopperhouse.model.domain;

public enum TIPO_ENDERECO {

    COBRANCA("Cobrança"),
    ENTREGA("Entrega");

    private String label;

    TIPO_ENDERECO(String string) {
        setLabel(string);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

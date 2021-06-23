package br.com.fatec.chopperhouse.model.domain;

public enum TIPO_CLIENTE {
    BrainPoint("Brain Point"),
    HibridForm("Hibrid Form"),
    MonsterPoint("Monster Point"),
    Admin("Brain Point");

    TIPO_CLIENTE(String s) {
        this.nome = s;
    }
    private String nome;
}

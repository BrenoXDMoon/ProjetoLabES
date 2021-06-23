package br.com.fatec.chopperhouse.model.domain;

import java.util.Calendar;

public class Documento extends EntidadeDominio{

    private Integer id;
    private String codigo;
    private Calendar validade;
    private TIPO_DOCUMENTO tipoDocumento;

}

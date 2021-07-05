package br.com.fatec.chopperhouse.dto.request;

import br.com.fatec.chopperhouse.models.TipoDocumento;
import lombok.Data;

@Data
public class DocumentoDTO {
    private Integer id;
    private TipoDocumento tipoDocumento;
    private String codigo;
    private String validade;
}

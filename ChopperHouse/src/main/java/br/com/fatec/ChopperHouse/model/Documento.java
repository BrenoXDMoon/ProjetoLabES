package br.com.fatec.ChopperHouse.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Documento extends EntidadeDominio{

    @Id
    private Integer id;
    private TipoDocumento tipoDocumento;
    private String codigo;
    private Date validade; 
}

package br.com.fatec.ChopperHouse.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente extends EntidadeDominio{

    @Id
    private Integer id;
    private String nomeCompleto;
    @Enumerated
    private GENERO genero;
    @OneToMany
    private List<Documento> documentos;
    @OneToMany
    private List<Endereco> enderecos;

    private Telefone telefone;
    
}

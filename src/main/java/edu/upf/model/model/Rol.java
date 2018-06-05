package edu.upf.model.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="MODEL_ROL")
@Getter
@Setter
@EqualsAndHashCode(of="idRol")
public class Rol {

	@Id
    @Column(name = "IDROL")
    private Long idRol;
     
    @Column(name = "MEMO")
    private String memo;
    
    @Column(name = "ASSIGNABLESN")
    private String assignableSN;
    
    @Column(name = "USERALTA")
    private Long userAlta;
    
    @Column(name = "DATAALTA")
    private Date dataAlta;
    
    @Column(name = "USERMODI")
    private Long userModi;
    
    @Column(name = "DATAMODI")
    private Date dataModi;
    
    /* El rol és l'invers side de la relació Many2Many amb els usuaris */
    /* A l'atribut mappedBy posem el nom de la Collection que mapeja els rols a la classe Usuari */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "rols")
    private Set<Usuari> usuaris = new HashSet<>();
    
}
